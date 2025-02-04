package com.appvantage.quizapp.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel:ViewModel() {

    private val _homeState = MutableStateFlow(StateHomeScreen())
    val homeState = _homeState.asStateFlow()

    fun onEvent(event:EventHomeScreen){
        when(event){
            is EventHomeScreen.SetNumberOfQuizzes->{
                _homeState.value = _homeState.value.copy(numberOfQuiz = event.numberOfQuizzes)
            }

            is EventHomeScreen.SetQuizCategory->{
                _homeState.value = _homeState.value.copy(category = event.category)

            }

            is EventHomeScreen.SetQuizDifficulty->{
                _homeState.value = _homeState.value.copy(difficulty = event.difficulty)

            }

            is EventHomeScreen.SetQuizType->{
                _homeState.value = _homeState.value.copy(type = event.type)

            }


            else ->{}
        }
    }
}