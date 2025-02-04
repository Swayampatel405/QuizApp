package com.appvantage.quizapp.domain.repository

import com.appvantage.quizapp.domain.model.Quiz

interface QuizRepository {

    suspend fun getQuizzes(
        amount : Int,
        category : Int,
        difficulty : String,
        type : String,
    ):List<Quiz>
}