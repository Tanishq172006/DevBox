package com.example.devbox.features.regex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegexViewModel : ViewModel() {

    var regexPattern by mutableStateOf("")

    var sampleText by mutableStateOf("")

    var matches by mutableStateOf<List<String>>(
        emptyList()
    )

    var error by mutableStateOf("")

    var groups by mutableStateOf<List<RegexGroup>>(
        emptyList()
    )

    fun testRegex() {

        try {

            matches =
                RegexUtils.findMatches(
                    regexPattern,
                    sampleText
                )

            groups =
                RegexUtils.extractGroups(
                    regexPattern,
                    sampleText
                )

            error = ""

        } catch (e: Exception) {

            error =
                e.message ?: "Invalid Regex"
        }
    }
}