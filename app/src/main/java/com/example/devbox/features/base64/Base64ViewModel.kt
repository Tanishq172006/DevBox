package com.example.devbox.features.base64

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Base64ToolViewModel : ViewModel() {

    var input by mutableStateOf("")

    var output by mutableStateOf("")

    fun encode() {

        output =
            Base64Utils.encode(input)
    }

    fun decode() {

        output =
            Base64Utils.decode(input)
    }
}