package com.easyapps.example.testlistc.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}