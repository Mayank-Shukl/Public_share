package com.easyapps.example.testlistc.repository

import com.easyapps.example.testlistc.db.JokeDao
import com.easyapps.example.testlistc.db.JokeEntity
import com.easyapps.example.testlistc.service.JokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class JokeRepository @Inject constructor(
    private val jokeService: JokeService,
    private val jokeDao: JokeDao
) : IJokeRepository {
    override suspend fun getJoke() = flow {
        var jokeResponse = jokeService.getJoke()
        var jokeBody = jokeResponse.body()
        while (!jokeResponse.isSuccessful || jokeBody == null || jokeDao.hasJoke(jokeBody.id.toString()) > 0) {
            jokeResponse = jokeService.getJoke()
            jokeBody = jokeResponse.body()
        }

        val jokeString = jokeBody.setup + "\n" + jokeBody.delivery
        val joke = JokeEntity(jokeBody.id.toString(), jokeString)
        jokeDao.insert(JokeEntity(jokeBody.id.toString(), jokeString))
        emit(joke)
    }.flowOn(Dispatchers.IO)

    override suspend fun saveFavorite(value: JokeEntity) = withContext(Dispatchers.IO) {
        jokeDao.insert(value.copy(fav = true))
    }

    override suspend fun getAllJokes(): Flow<List<JokeEntity>> = flow {
        emit(jokeDao.getAllJokes().filter { it.joke.isNotEmpty() })
    }.flowOn(Dispatchers.IO)

    override suspend fun getFavJokes() = flow {
        emit(jokeDao.getAllFavJokes())
    }.flowOn(Dispatchers.IO)

}


interface IJokeRepository {
    suspend fun getJoke(): Flow<JokeEntity>
    suspend fun saveFavorite(value: JokeEntity)
    suspend fun getAllJokes(): Flow<List<JokeEntity>>
    suspend fun getFavJokes(): Flow<List<JokeEntity>>
}