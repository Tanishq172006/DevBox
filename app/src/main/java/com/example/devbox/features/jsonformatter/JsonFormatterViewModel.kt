package com.example.devbox.features.jsonformatter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx. compose.runtime.getValue

class JsonFormatterViewModel: ViewModel() {
    var inputText by mutableStateOf("")
    var outputText by mutableStateOf("")

    fun beautifyJson(){
        outputText = JsonFormatterUtils.beautifyJson(inputText)
    }

    fun minifyJson(){
        outputText = JsonFormatterUtils.minifyJson(inputText)
    }
}