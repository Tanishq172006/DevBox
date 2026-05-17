package com.example.devbox.features.apitester

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ApiTesterViewModelFactory(

    private val application: Application

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(

        modelClass: Class<T>

    ): T {

        return ApiTesterViewModel(
            application
        ) as T
    }
}