package com.example.devbox.features.base64

import android.util.Base64

object Base64Utils {

    fun encode(text: String): String {

        return Base64.encodeToString(
            text.toByteArray(),
            Base64.DEFAULT
        )
    }

    fun decode(base64: String): String {

        return try {

            String(
                Base64.decode(
                    base64,
                    Base64.DEFAULT
                )
            )

        } catch (e: Exception) {

            "Invalid Base64"
        }
    }
}