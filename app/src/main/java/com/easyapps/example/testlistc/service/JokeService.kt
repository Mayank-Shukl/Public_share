package com.easyapps.example.testlistc.service

import com.easyapps.example.testlistc.model.JokeResponse
import retrofit2.Response
import retrofit2.http.GET


interface JokeService {

    @GET("joke/Any")
    suspend fun getJoke(): Response<JokeResponse>

    companion object {
        const val BASE_URL = "https://v2.jokeapi.dev/"
    }
}