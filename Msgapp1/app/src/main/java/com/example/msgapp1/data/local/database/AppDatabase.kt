package com.example.msgapp1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.msgapp1.data.local.dao.MessageDao
import com.example.msgapp1.model.Message

@Database(entities = [Message::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun messageDao(): MessageDao
}


