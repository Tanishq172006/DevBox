package com.example.devbox.features.graphql

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.devbox.core.models.HeaderItem
import com.example.devbox.features.jsonformatter.JsonFormatterUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class GraphQLViewModel : ViewModel() {

    var endpoint by mutableStateOf(
        "https://countries.trevorblades.com/graphql"
    )

    var query by mutableStateOf("")

    var variables by mutableStateOf("{}")

    var response by mutableStateOf("")

    var loading by mutableStateOf(false)

    var headers =
        mutableStateListOf(
            HeaderItem()
        )

    fun executeQuery() {

        loading = true

        Thread {

            try {

                val client =
                    OkHttpClient()

                val requestJson = """

                    {
                      "query": ${query.quote()},
                      "variables": $variables
                    }

                """.trimIndent()

                val requestBody =
                    requestJson.toRequestBody(

                        "application/json"
                            .toMediaType()
                    )

                val requestBuilder =
                    Request.Builder()
                        .url(endpoint)

                headers.forEach { header ->

                    if (
                        header.key.isNotBlank()
                    ) {

                        requestBuilder.addHeader(

                            header.key,

                            header.value
                        )
                    }
                }

                val request =
                    requestBuilder
                        .post(requestBody)
                        .build()

                val result =
                    client.newCall(request)
                        .execute()

                response =

                    JsonFormatterUtils
                        .beautifyJson(

                            result.body?.string()
                                ?: "No Response"
                        )

            } catch (e: Exception) {

                response =
                    e.message ?: "Error"
            }

            loading = false

        }.start()
    }

    private fun String.quote(): String {

        return buildString {

            append("\"")

            append(

                this@quote
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
            )

            append("\"")
        }
    }
}