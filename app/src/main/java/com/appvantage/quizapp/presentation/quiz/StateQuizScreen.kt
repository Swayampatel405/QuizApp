package com.appvantage.quizapp.presentation.quiz

import com.appvantage.quizapp.domain.model.Quiz
import java.lang.Error

data class StateQuizScreen(

    val isLoading:Boolean = false,
    val quizState : List<QuizState>  = listOf(),
    val error: String = "",
    val score: Int = 0
)

data class QuizState(

    val quiz: Quiz? =null,
    val shuffledOptions:List<String> = emptyList(),
    val selectedOptions:Int ? =-1
)
