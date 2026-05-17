package com.example.devbox.features.history

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.devbox.data.local.ApiHistoryEntity
import com.example.devbox.data.local.AppDatabase
import kotlinx.coroutines.launch

class HistoryViewModel(

    application: Application

) : AndroidViewModel(application) {

    private val database =

        AppDatabase.getDatabase(
            application
        )

    var historyList =
        mutableStateListOf<ApiHistoryEntity>()

    init {

        loadHistory()
    }

    fun loadHistory() {

        viewModelScope.launch {

            historyList.clear()

            historyList.addAll(

                database
                    .apiHistoryDao()
                    .getAll()
            )
        }
    }

    fun deleteItem(
        id: Int
    ) {

        viewModelScope.launch {

            database
                .apiHistoryDao()
                .deleteById(id)

            loadHistory()
        }
    }

    fun clearAll() {

        viewModelScope.launch {

            database
                .apiHistoryDao()
                .clearAll()

            loadHistory()
        }
    }
}