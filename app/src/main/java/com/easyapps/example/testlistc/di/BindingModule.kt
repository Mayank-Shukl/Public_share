package com.easyapps.example.testlistc.di

import com.easyapps.example.testlistc.repository.IJokeRepository
import com.easyapps.example.testlistc.repository.JokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    abstract fun bindRepo(jokeRepository: JokeRepository):IJokeRepository
}