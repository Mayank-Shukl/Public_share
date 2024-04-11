package com.easyapps.example.testlistc.di

import android.content.Context
import androidx.room.Room
import com.easyapps.example.testlistc.db.AppDatabase
import com.easyapps.example.testlistc.service.JokeService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(JokeService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()


    @Provides
    @Singleton
    fun provideJokeService(retrofit: Retrofit): JokeService =
        retrofit.create(JokeService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(45, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app-db"
    ).build()

    @Provides
    @Singleton
    fun provideJokeDao(appDatabase: AppDatabase) = appDatabase.jokeDao()

}