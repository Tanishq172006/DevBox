package com.example.devbox.features.graphql

data class GraphQLRequest(

    val query: String,

    val variables: Map<String, Any>? = null
)