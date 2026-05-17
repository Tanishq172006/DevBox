package com.example.devbox.features.apitester

data class CurlRequest(
    val method: String,
    val url: String,
    val headers: List<Pair<String, String>>,
    val body: String
)