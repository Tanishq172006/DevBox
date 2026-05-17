package com.example.devbox.features.jsonformatter


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

object JsonSyntaxHighlighter {

    fun highlight(json: String): AnnotatedString {

        return buildAnnotatedString {

            val regex = Regex(
                "\"(.*?)\"(?=\\s*:)|\".*?\"|\\b(true|false|null)\\b|-?\\d+(\\.\\d+)?"
            )

            var lastIndex = 0

            regex.findAll(json).forEach { match ->

                append(json.substring(lastIndex, match.range.first))

                val value = match.value

                when {

                    value.matches(Regex("\"(.*?)\"(?=\\s*:)") ) -> {
                        withStyle(
                            SpanStyle(color = Color(0xFF64B5F6))
                        ) {
                            append(value)
                        }
                    }

                    value.startsWith("\"") -> {
                        withStyle(
                            SpanStyle(color = Color(0xFF81C784))
                        ) {
                            append(value)
                        }
                    }

                    value.matches(Regex("-?\\d+(\\.\\d+)?")) -> {
                        withStyle(
                            SpanStyle(color = Color(0xFFFFB74D))
                        ) {
                            append(value)
                        }
                    }

                    else -> {
                        withStyle(
                            SpanStyle(color = Color(0xFFBA68C8))
                        ) {
                            append(value)
                        }
                    }
                }

                lastIndex = match.range.last + 1
            }

            append(json.substring(lastIndex))
        }
    }
}