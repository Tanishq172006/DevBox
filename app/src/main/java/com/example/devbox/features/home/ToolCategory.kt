package com.example.devbox.core.models

import com.example.devbox.features.home.ToolItem

data class ToolCategory(

    val title: String,

    val tools: List<ToolItem>
)