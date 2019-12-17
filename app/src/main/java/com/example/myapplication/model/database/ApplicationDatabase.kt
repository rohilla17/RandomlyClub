package com.example.myapplication.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}