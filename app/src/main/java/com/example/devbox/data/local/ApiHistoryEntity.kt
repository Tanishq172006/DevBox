package com.example.devbox.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "api_history")
data class ApiHistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val method: String,

    val url: String,

    val body: String,

    val timestamp: Long
)