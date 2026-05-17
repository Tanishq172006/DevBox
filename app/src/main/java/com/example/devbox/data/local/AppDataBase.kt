package com.example.devbox.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(

    entities = [
        ApiHistoryEntity::class
    ],

    version = 1
)
abstract class AppDatabase :
    RoomDatabase() {

    abstract fun apiHistoryDao():
            ApiHistoryDao

    companion object {

        @Volatile
        private var INSTANCE:
                AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(

                    context.applicationContext,

                    AppDatabase::class.java,

                    "devbox_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}