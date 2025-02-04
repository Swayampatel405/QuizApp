package com.appvantage.quizapp.domain.di

import com.appvantage.quizapp.data.remote.QuizApi
import com.appvantage.quizapp.data.repository.QuizRepositoryImpl
import com.appvantage.quizapp.domain.model.Quiz
import com.appvantage.quizapp.domain.repository.QuizRepository
import com.appvantage.quizapp.domain.usecases.GetQuizzesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository):GetQuizzesUseCases{
        return GetQuizzesUseCases(quizRepository)
    }

}