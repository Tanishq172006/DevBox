package com.example.devbox.features.sql

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SqlPlaygroundViewModel : ViewModel() {

    var query by mutableStateOf("")

    var result by mutableStateOf("")

    var tableData by mutableStateOf<SqlTableData?>(
        null
    )

    fun execute() {

        if (

            query.trim()
                .startsWith(
                    "SELECT",
                    true
                )
        ) {

            tableData =
                SqlUtils.executeSelectTable(
                    query
                )

            result = ""

        } else {

            result =
                SqlUtils.executeQuery(
                    query
                )

            tableData = null
        }
    }
}