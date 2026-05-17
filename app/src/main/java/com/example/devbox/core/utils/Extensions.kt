package com.example.devbox.core.utils

import org.json.JSONArray
import org.json.JSONObject

fun JSONObject.toMap(): Map<String, Any?> {

    val map = mutableMapOf<String, Any?>()

    keys().forEach { key ->

        map[key] = when (val value = this[key]) {

            is JSONObject -> value.toMap()

            is JSONArray -> value.toList()

            else -> value
        }
    }

    return map
}

fun JSONArray.toList(): List<Any?> {

    val list = mutableListOf<Any?>()

    for (i in 0 until length()) {

        val value = this[i]

        list.add(

            when (value) {

                is JSONObject -> value.toMap()

                is JSONArray -> value.toList()

                else -> value
            }
        )
    }

    return list
}