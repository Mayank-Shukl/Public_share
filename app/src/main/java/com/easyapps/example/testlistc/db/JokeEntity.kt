package com.easyapps.example.testlistc.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke")
data class JokeEntity(
    @PrimaryKey
    val id: String,
    val joke: String,
    val fav: Boolean = false
)