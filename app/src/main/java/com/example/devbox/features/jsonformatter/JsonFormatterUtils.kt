package com.example.devbox.features.jsonformatter

import org.json.JSONArray
import org.json.JSONObject

object JsonFormatterUtils {
    fun beautifyJson(input: String): String {

        return try {

            if (input.trim().startsWith("{")) {
                JSONObject(input).toString(4)
            } else {
                JSONArray(input).toString(4)
            }

        } catch (e: Exception) {
            "Invalid JSON"
        }
    }

    fun minifyJson(input: String): String {

        return try {

            if (input.trim().startsWith("{")) {
                JSONObject(input).toString()
            } else {
                JSONArray(input).toString()
            }

        } catch (e: Exception) {
            "Invalid JSON"
        }
    }
}