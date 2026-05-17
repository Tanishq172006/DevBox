package com.example.devbox.features.jsonkotlin

import org.json.JSONArray
import org.json.JSONObject

object JsonToKotlinUtils {

    private val generatedClasses =
        mutableSetOf<String>()

    private val classBuilder =
        StringBuilder()

    fun generateDataClass(

        json: String,

        rootClassName: String = "Root"

    ): String {

        return try {

            generatedClasses.clear()

            classBuilder.clear()

            val jsonObject =
                JSONObject(json)

            generateClass(
                rootClassName,
                jsonObject
            )

            classBuilder.toString()

        } catch (e: Exception) {

            "Invalid JSON"
        }
    }

    private fun generateClass(

        className: String,

        jsonObject: JSONObject

    ) {

        if (
            generatedClasses.contains(className)
        ) return

        generatedClasses.add(className)

        val properties =
            mutableListOf<String>()

        val keys =
            jsonObject.keys()

        while (keys.hasNext()) {

            val key =
                keys.next()

            val value =
                jsonObject.opt(key)

            val type =
                getType(
                    key,
                    value
                )

            properties.add(

                "    val $key: $type"
            )
        }

        classBuilder.append(

            "@kotlinx.serialization.Serializable\n"
        )

        classBuilder.append(

            "data class $className(\n"
        )

        classBuilder.append(

            properties.joinToString(
                ",\n"
            )
        )

        classBuilder.append(
            "\n)\n\n"
        )
    }

    private fun getType(

        key: String,

        value: Any?

    ): String {

        return when (value) {

            null -> "String?"

            is Int -> "Int"

            is Long -> "Long"

            is Double -> "Double"

            is Boolean -> "Boolean"

            is String -> "String"

            is JSONObject -> {

                val nestedClassName =
                    key.toClassName()

                generateClass(
                    nestedClassName,
                    value
                )

                nestedClassName
            }

            is JSONArray -> {

                if (value.length() == 0) {

                    "List<Any>"
                } else {

                    val first =
                        value.get(0)

                    when (first) {

                        is JSONObject -> {

                            val nestedClassName =
                                key.toClassName()

                            generateClass(
                                nestedClassName,
                                first
                            )

                            "List<$nestedClassName>"
                        }

                        is Int -> "List<Int>"

                        is Long -> "List<Long>"

                        is Double -> "List<Double>"

                        is Boolean -> "List<Boolean>"

                        is String -> "List<String>"

                        else -> "List<Any>"
                    }
                }
            }

            else -> "Any"
        }
    }

    private fun String.toClassName():
            String {

        return replaceFirstChar {

            it.uppercase()
        }
    }
}