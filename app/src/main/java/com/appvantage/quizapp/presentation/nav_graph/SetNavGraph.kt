package com.appvantage.quizapp.presentation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.appvantage.quizapp.presentation.authentication.AuthViewModel
import com.appvantage.quizapp.presentation.authentication.pages.LoginPage
import com.appvantage.quizapp.presentation.authentication.pages.SignupPage
import com.appvantage.quizapp.presentation.home.HomeScreen
import com.appvantage.quizapp.presentation.home.HomeScreenViewModel
import com.appvantage.quizapp.presentation.quiz.QuizScreen
import com.appvantage.quizapp.presentation.quiz.QuizViewModel
import com.appvantage.quizapp.presentation.score.ScoreScreen

@Composable
fun SetNavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginPage.route
    ) {
        composable(route = Routes.LoginPage.route){
            val authViewModel : AuthViewModel = AuthViewModel()
            LoginPage(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(route = Routes.SignupPage.route){
            val authViewModel : AuthViewModel = AuthViewModel()
            SignupPage(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(route = Routes.HomeScreen.route){
            val authViewModel : AuthViewModel = AuthViewModel()
            val viewModel : HomeScreenViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(state = state, event = viewModel::onEvent,navController=navController, authViewModel = authViewModel )
        }

        composable(
            route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_QUIZ_NUMBER){type = NavType.IntType},
                navArgument(ARG_KEY_QUIZ_CATEGORY){type = NavType.StringType},
                navArgument(ARG_KEY_QUIZ_DIFFICULTY){type = NavType.StringType},
                navArgument(ARG_KEY_QUIZ_TYPE){type = NavType.StringType}
            )
        ){
            val numberOfQuizzes = it.arguments?.getInt(ARG_KEY_QUIZ_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_QUIZ_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_QUIZ_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_QUIZ_TYPE)

            val quizViewModel : QuizViewModel = hiltViewModel()
            val state by quizViewModel.quizList.collectAsState()
            QuizScreen(
                numOfQuiz= numberOfQuizzes!!,
                quizCategory= category!!,
                quizDifficulty= difficulty!! ,
                quizType = type!!,
                event = quizViewModel::onEvent,
                state = state,
                navController,
                viewModel = quizViewModel
            )

        }
        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(NOQ_KEY){type = NavType.IntType},
                navArgument(CORRECT_ANS_KEY){type = NavType.IntType},
                navArgument(USER_ANSWERS_KEY) { type = NavType.StringType },
                navArgument(CORRECT_ANSWERS_KEY) { type = NavType.StringType },
                navArgument(QUESTIONS_KEY) { type = NavType.StringType }
            )
        ){
            val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
            val numOfCorrectAns = it.arguments?.getInt(CORRECT_ANS_KEY)
            val userAnswers = it.arguments?.getString(USER_ANSWERS_KEY)?.split("|")
            val correctAnswers = it.arguments?.getString(CORRECT_ANSWERS_KEY)?.split("|")
            val questions = it.arguments?.getString(QUESTIONS_KEY)?.split("|")
            ScoreScreen(
                numOfQuestions = numOfQuestions!!,
                numOfCorrectAns = numOfCorrectAns!!,
                userAnswers = userAnswers!!,
                correctAnswers = correctAnswers!!,
                questions = questions!!,
                navController = navController
            )
        }
    }
}