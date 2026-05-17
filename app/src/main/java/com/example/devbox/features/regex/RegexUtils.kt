package com.example.devbox.features.regex

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle


object RegexUtils {

    fun findMatches(

        pattern: String,

        text: String

    ): List<String> {

        return try {

            Regex(pattern)

                .findAll(text)

                .map {
                    it.value
                }

                .toList()

        } catch (e: Exception) {

            emptyList()
        }
    }


    fun highlightMatches(

        pattern: String,

        text: String

    ): AnnotatedString {

        return try {

            buildAnnotatedString {

                val regex = Regex(pattern)

                var lastIndex = 0

                regex.findAll(text).forEach { match ->

                    append(
                        text.substring(
                            lastIndex,
                            match.range.first
                        )
                    )

                    withStyle(

                        SpanStyle(
                            background =
                                Color.Yellow,

                            color =
                                Color.Black
                        )
                    ) {

                        append(match.value)
                    }

                    lastIndex =
                        match.range.last + 1
                }

                append(
                    text.substring(lastIndex)
                )
            }

        } catch (e: Exception) {

            AnnotatedString(text)
        }
    }
    fun extractGroups(

        pattern: String,

        text: String

    ): List<RegexGroup> {

        return try {

            Regex(pattern)

                .findAll(text)

                .map { match ->

                    RegexGroup(

                        fullMatch =
                            match.value,

                        groups =
                            match.groupValues
                                .drop(1)
                    )
                }

                .toList()

        } catch (e: Exception) {

            emptyList()
        }
    }

    val regexPresets = listOf(

        RegexPreset(

            name = "Email",

            pattern =
                "(\\w+)@(\\w+\\.\\w+)"
        ),

        RegexPreset(

            name = "URL",

            pattern =
                "https?://[\\w./?=&-]+"
        ),

        RegexPreset(

            name = "Phone",

            pattern =
                "\\d{10}"
        ),

        RegexPreset(

            name = "Hex Color",

            pattern =
                "#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})"
        ),

        RegexPreset(

            name = "IP Address",

            pattern =
                "(\\d{1,3}\\.){3}\\d{1,3}"
        )
    )
}