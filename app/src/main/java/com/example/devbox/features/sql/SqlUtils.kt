package com.example.devbox.features.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase

object SqlUtils {

    private var database: SQLiteDatabase? = null

    fun initDatabase(context: Context) {

        if (database == null) {

            database =
                context.openOrCreateDatabase(

                    "playground.db",

                    Context.MODE_PRIVATE,

                    null
                )
        }
    }

    fun executeQuery(
        query: String
    ): String {

        return try {

            if (
                query.trim()
                    .startsWith(
                        "SELECT",
                        true
                    )
            ) {

                "SELECT_QUERY"

            } else {

                database?.execSQL(query)

                "Query Executed Successfully"
            }

        } catch (e: Exception) {

            e.message ?: "SQL Error"
        }
    }

    fun executeSelectTable(

        query: String

    ): SqlTableData? {

        return try {

            val cursor =
                database?.rawQuery(
                    query,
                    null
                )

            if (
                cursor == null
            ) {

                return null
            }

            val columns =
                cursor.columnNames.toList()

            val rows =
                mutableListOf<List<String>>()

            while (cursor.moveToNext()) {

                val row =
                    mutableListOf<String>()

                for (
                i in columns.indices
                ) {

                    row.add(

                        cursor.getString(i)
                            ?: "null"
                    )
                }

                rows.add(row)
            }

            cursor.close()

            SqlTableData(
                columns = columns,
                rows = rows
            )

        } catch (e: Exception) {

            null
        }
    }
}