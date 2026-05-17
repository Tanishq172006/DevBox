package com.example.devbox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ApiHistoryDao {

    @Insert
    suspend fun insert(
        history: ApiHistoryEntity
    )

    @Query(
        "SELECT * FROM api_history ORDER BY timestamp DESC"
    )
    suspend fun getAll():
            List<ApiHistoryEntity>

    @Query(
        "DELETE FROM api_history"
    )
    suspend fun clearAll()

    @Query(
        "DELETE FROM api_history WHERE id = :id"
    )
    suspend fun deleteById(
        id: Int
    )
}

