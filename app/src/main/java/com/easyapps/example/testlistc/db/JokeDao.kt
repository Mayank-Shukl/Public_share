package com.easyapps.example.testlistc.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface JokeDao {

    @Query("SELECT count(*) FROM joke WHERE id = :id")
    fun hasJoke(id: String): Int

    @Upsert
    fun insert(jokeEntity: JokeEntity)

    @Query("SELECT * FROM joke")
    fun getAllJokes(): List<JokeEntity>

    @Query("SELECT * FROM joke where fav = 1")
    fun getAllFavJokes(): List<JokeEntity>

}