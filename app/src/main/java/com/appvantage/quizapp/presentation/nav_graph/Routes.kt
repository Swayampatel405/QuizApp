package com.appvantage.quizapp.presentation.nav_graph


const val ARG_KEY_QUIZ_NUMBER = "ak_quiz_number"
const val ARG_KEY_QUIZ_CATEGORY = "ak_quiz_category"
const val ARG_KEY_QUIZ_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_QUIZ_TYPE = "ak_quiz_type"
const val NOQ_KEY = "noq_key"
const val CORRECT_ANS_KEY = "correct_ans_key"
const val USER_ANSWERS_KEY = "user_answers_key"
const val CORRECT_ANSWERS_KEY = "correct_answers_key"
const val QUESTIONS_KEY = "questions_key"

sealed class Routes(val route:String) {

    object LoginPage : Routes("login")

    object SignupPage : Routes("signup")

    object HomeScreen : Routes("home_screen")

    object QuizScreen :
        Routes("quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}") {

        fun quizParams(
            numOfQuizzes: Int,
            category: String,
            difficulty: String,
            type: String
        ): String {

            return "quiz_screen/{$ARG_KEY_QUIZ_NUMBER}/{$ARG_KEY_QUIZ_CATEGORY}/{$ARG_KEY_QUIZ_DIFFICULTY}/{$ARG_KEY_QUIZ_TYPE}"
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_NUMBER}",
                    newValue = numOfQuizzes.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_CATEGORY}",
                    newValue = category.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_DIFFICULTY}",
                    newValue = difficulty.toString()
                )
                .replace(
                    oldValue = "{$ARG_KEY_QUIZ_TYPE}",
                    newValue = type.toString()
                )

        }

    }
    object ScoreScreen : Routes("score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}/{$USER_ANSWERS_KEY}/{$CORRECT_ANSWERS_KEY}/{$QUESTIONS_KEY}"){
        fun passNumOfQuestionsAndCorrectAns(
            numOfQuestions: Int,
            numOfCorrectAnswers: Int,
            userAnswers: List<List<String?>>,
            correctAnswers: List<String>,
            questions: List<String>
        ):String
        {
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}/{$USER_ANSWERS_KEY}/{$CORRECT_ANSWERS_KEY}/{$QUESTIONS_KEY}"
                .replace("{$NOQ_KEY}", numOfQuestions.toString())
                .replace("{$CORRECT_ANS_KEY}", numOfCorrectAnswers.toString())
                .replace("{$USER_ANSWERS_KEY}", userAnswers.joinToString("|"))
                .replace("{$CORRECT_ANSWERS_KEY}", correctAnswers.joinToString("|"))
                .replace("{$QUESTIONS_KEY}", questions.joinToString("|"))
        }
    }
}
