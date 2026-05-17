package com.example.devbox.features.jwt


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class JwtDecoderViewModel : ViewModel() {

    var jwtInput by mutableStateOf("")

    var header by mutableStateOf("")

    var payload by mutableStateOf("")

    var expirationStatus by mutableStateOf("")

    var expirationDate by mutableStateOf("")

    fun decode() {

        val result =
            JwtUtils.decodeJwt(jwtInput)

        header = result.first

        payload = result.second

        checkExpiration()
    }

    private fun checkExpiration() {

        try {

            val payloadJson =
                org.json.JSONObject(payload)

            if (payloadJson.has("exp")) {

                val exp =
                    payloadJson.getLong("exp")

                val expMillis =
                    exp * 1000

                val current =
                    System.currentTimeMillis()

                val date =
                    java.text.SimpleDateFormat(
                        "dd MMM yyyy hh:mm a",
                        java.util.Locale.getDefault()
                    ).format(
                        java.util.Date(expMillis)
                    )

                expirationDate = date

                expirationStatus =

                    if (current > expMillis) {

                        "Expired"

                    } else {

                        "Valid"
                    }

            } else {

                expirationStatus =
                    "No expiration"

                expirationDate = "-"
            }

        } catch (e: Exception) {

            expirationStatus = "Invalid"

            expirationDate = "-"
        }
    }
}