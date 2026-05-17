package com.example.devbox.features.apitester

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.devbox.core.models.HeaderItem
import com.example.devbox.features.jsonformatter.JsonFormatterUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.devbox.data.local.ApiHistoryEntity
import com.example.devbox.data.local.AppDatabase
import kotlinx.coroutines.launch

class ApiTesterViewModel(

    application: Application

) : AndroidViewModel(application){

    var url by mutableStateOf("")
    var responseText by mutableStateOf("")
    var loading by mutableStateOf(false)

    var statusCode by mutableStateOf("")
    var responseTime by mutableStateOf("")

    var selectedMethod by mutableStateOf("GET")

    var requestBody by mutableStateOf("")

    var headers = mutableStateListOf(
        HeaderItem()
    )

    private val database =

        AppDatabase.getDatabase(
            application
        )

    fun sendRequest() {

        loading = true

        val client = OkHttpClient()

        Thread {

            try {

                val startTime =
                    System.currentTimeMillis()

                val requestBuilder =
                    Request.Builder()
                        .url(url)

                headers.forEach { header ->

                    if (
                        header.key.isNotBlank() &&
                        header.value.isNotBlank()
                    ) {

                        requestBuilder.addHeader(
                            header.key,
                            header.value
                        )
                    }
                }

                val requestBodyObject =
                    requestBody.toRequestBody(
                        "application/json"
                            .toMediaType()
                    )

                when (selectedMethod) {

                    "GET" -> {
                        requestBuilder.get()
                    }

                    "POST" -> {
                        requestBuilder.post(
                            requestBodyObject
                        )
                    }

                    "PUT" -> {
                        requestBuilder.put(
                            requestBodyObject
                        )
                    }

                    "DELETE" -> {
                        requestBuilder.delete(
                            requestBodyObject
                        )
                    }
                }

                val response =
                    client.newCall(
                        requestBuilder.build()
                    ).execute()

                val endTime =
                    System.currentTimeMillis()

                statusCode =
                    response.code.toString()

                responseTime =
                    "${endTime - startTime} ms"

                responseText =
                    JsonFormatterUtils
                        .beautifyJson(
                            response.body?.string()
                                ?: "No Response"
                        )

                viewModelScope.launch {

                    database.apiHistoryDao().insert(

                        ApiHistoryEntity(

                            method = selectedMethod,

                            url = url,

                            body = requestBody,

                            timestamp =
                                System.currentTimeMillis()
                        )
                    )
                }

            } catch (e: Exception) {

                responseText =
                    e.message ?: "Error"

            }

            loading = false

        }.start()
    }
}