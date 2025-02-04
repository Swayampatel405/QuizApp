package com.appvantage.quizapp.data.remote.dto

import com.appvantage.quizapp.domain.model.Quiz

data class QuizResponse(
    val response_code: Int,
    val results: List<Quiz>
)