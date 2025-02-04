package com.appvantage.quizapp.data.repository

import com.appvantage.quizapp.data.remote.QuizApi
import com.appvantage.quizapp.domain.model.Quiz
import com.appvantage.quizapp.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val quizApi: QuizApi
):QuizRepository {
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
        return quizApi.getQuizzes(amount, category, difficulty, type).results
    }
}