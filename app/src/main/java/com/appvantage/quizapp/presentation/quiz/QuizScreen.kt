package com.appvantage.quizapp.presentation.quiz

import android.os.CountDownTimer
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.appvantage.quizapp.R
import com.appvantage.quizapp.presentation.common.ButtonBox
import com.appvantage.quizapp.presentation.common.QuizAppBar
import com.appvantage.quizapp.presentation.home.component.ShimmerEffectQuizInterface
import com.appvantage.quizapp.presentation.nav_graph.Routes
import com.appvantage.quizapp.presentation.quiz.component.QuizInterface
import com.appvantage.quizapp.presentation.util.Constants
import com.appvantage.quizapp.presentation.util.Dimens
import kotlinx.coroutines.launch


@Composable
fun QuizScreen(
    numOfQuiz:Int,
    quizCategory:String,
    quizDifficulty: String,
    quizType: String,
    event: (EventQuizScreen)->Unit,
    state:StateQuizScreen,
    navController: NavController,
    viewModel: QuizViewModel
){

    val totalTime = 1 * 60 * 1000L // 1 min
    var timeLeft by remember { mutableStateOf(totalTime) }
    val timer = remember {
        object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
            }

            override fun onFinish() {
//                navController.navigate(passNumOfQuestionsAndCorrectAns(state.quizState.size,state.score)
            }
        }
    }

    BackHandler {
        navController.navigate(Routes.HomeScreen.route){
            popUpTo(Routes.HomeScreen.route){inclusive=true}
        }
    }
    LaunchedEffect(key1 = Unit){
        val difficulty = when(quizDifficulty){
            "Medium" -> "medium"
            "Hard" -> "hard"
            else-> "easy"
        }
        val type = when(quizType){
            "Multiple Choice"->"multiple"
            else -> "boolean"
        }
        event(EventQuizScreen.GetQuizzes(numOfQuiz,Constants.categoriesMap[quizCategory]!!,difficulty,type))
        timer.start()
    }
    DisposableEffect(Unit){
        onDispose {
            timer.cancel()
        }
    }



    Column (
        modifier = Modifier.fillMaxSize()
    ){
        //TopBar
        QuizAppBar(quizCategory){
            navController.navigate(Routes.HomeScreen.route){
                popUpTo(Routes.HomeScreen.route){inclusive=true}
            }
        }

        Column (
            modifier = Modifier
                .padding(Dimens.SmallSpacerHeight)
                .fillMaxSize()
        ){
            Text(
                text = "Time Left: ${timeLeft / 1000}s",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))
            Column(modifier = Modifier.fillMaxWidth()){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Text(
                        text = "Questions: $numOfQuiz",
                        color = colorResource(R.color.blue_grey)
                    )
                    Text(
                        text = quizDifficulty,
                        color = colorResource(R.color.blue_grey)
                    )
                }

            }


            Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.VerySmallViewHeight)
                    .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                    .background(color = colorResource(R.color.blue_grey))
            )

            Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

            //quiz interface

            if(quizFetched(state)){
                val pagerState = rememberPagerState { state.quizState.size }

                HorizontalPager(state = pagerState) {index->
                    QuizInterface(
                        modifier = Modifier.weight(1f),
                        onOptionSelected = {selectedIndex->
                            event(EventQuizScreen.SetOptionSelected(index,selectedIndex))

                        },
                        quizState = state.quizState[index],
                        qNumber = index + 1
                    )
                }

                val buttonText by remember {
                    derivedStateOf {
                        when(pagerState.currentPage){
                           0 ->{
                               listOf("","Next")
                           }
                           state.quizState.size - 1 ->{
                               listOf("Previous","Submit")
                           }
                           else -> {
                                listOf("Previous","Next")
                           }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimens.MediumPadding)
                        .navigationBarsPadding()
                ){
                    val scope = rememberCoroutineScope()
                    if(buttonText[0].isNotEmpty()) {
                        ButtonBox(
                            modifier = Modifier.weight(1f),
                            text = "Previous",
                            padding = Dimens.SmallPadding,
                            fraction = 0.43f,
                            textColor = colorResource(R.color.black),
                            fontSize = Dimens.SmallTextSize
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }

                    ButtonBox(
                        modifier = Modifier.weight(1f),
                        text = buttonText[1],
                        padding = Dimens.SmallPadding,
                        borderColor = colorResource(R.color.orange),
                        containerColor = if(pagerState.currentPage == state.quizState.size - 1 ) colorResource(R.color.orange) else colorResource(R.color.dark_slate_blue),
                        fraction = 0.43f,
                        textColor = colorResource(R.color.white),
                        fontSize = Dimens.SmallTextSize
                    ) {
                        if(pagerState.currentPage == state.quizState.size - 1){
                            Log.d("QuizScreen", "Questions: ${state.quizState.map { it.quiz?.question }}")
                            viewModel.navigateToScoreScreen(
                                navController = navController,
                                numOfQuestions = state.quizState.size,
                                numOfCorrectAnswers = state.score,
                                userAnswer = listOf(state.quizState.map { it.userAnswers }),
                                correctAnswer = listOf(state.quizState.map { it.quiz?.correct_answer ?: "" }
                                    .toString()),
                                questions = state.quizState.map { it.quiz?.question ?: "" }
                            )
                        }
                        else{
                            scope.launch {  pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                        }

                    }
                }

            }


        }
    }
}

@Composable
fun quizFetched(state: StateQuizScreen): Boolean {
    return when {
        state.isLoading->{
            ShimmerEffectQuizInterface()
            false
        }
        state.quizState.isNotEmpty() ->{
            true
        }
        else->{
            Text(text = state.error, color = colorResource(R.color.white))
            false
        }
    }
}

//
//@Preview
//@Composable
//fun preQuiz(){
//
//    QuizScreen(
//        numOfQuiz = 12,
//        quizCategory = "HY",
//        quizDifficulty = "Easy",
//        quizType = "easy",
//        event = {},
//        state = StateQuizScreen(),
//        navController = rememberNavController()
//    )
//}