package com.appvantage.quizapp.data.di

import com.appvantage.quizapp.data.remote.QuizApi
import com.appvantage.quizapp.data.repository.QuizRepositoryImpl
import com.appvantage.quizapp.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideQuizApi():QuizApi{
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)

    }

    @Provides
    @Singleton
    fun provideQuizRepository(quizApi:QuizApi): QuizRepository {
        return QuizRepositoryImpl(quizApi)
    }
}