package com.example.devbox.features.jwt

import android.util.Base64
import org.json.JSONObject

object JwtUtils {

    fun decodeJwt(jwt: String): Pair<String, String> {

        return try {

            val parts = jwt.split(".")

            if (parts.size < 2) {

                Pair(
                    "Invalid JWT",
                    "Invalid JWT"
                )

            } else {

                val header = String(
                    Base64.decode(
                        parts[0],
                        Base64.URL_SAFE
                    )
                )

                val payload = String(
                    Base64.decode(
                        parts[1],
                        Base64.URL_SAFE
                    )
                )

                Pair(

                    JSONObject(header)
                        .toString(4),

                    JSONObject(payload)
                        .toString(4)
                )
            }

        } catch (e: Exception) {

            Pair(
                "Error",
                e.message ?: "Invalid JWT"
            )
        }
    }
}