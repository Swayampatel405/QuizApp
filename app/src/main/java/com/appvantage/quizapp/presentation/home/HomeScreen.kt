package com.appvantage.quizapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appvantage.quizapp.R
import com.appvantage.quizapp.presentation.authentication.AuthViewModel
import com.appvantage.quizapp.presentation.common.AppDropDownMenu
import com.appvantage.quizapp.presentation.common.ButtonBox
import com.appvantage.quizapp.presentation.home.component.DrawerContent
import com.appvantage.quizapp.presentation.home.component.HomeHeader
import com.appvantage.quizapp.presentation.nav_graph.Routes
import com.appvantage.quizapp.presentation.util.Constants
import com.appvantage.quizapp.presentation.util.Dimens
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    state:StateHomeScreen,
    event: (EventHomeScreen)->Unit,
    navController: NavController,
    authViewModel: AuthViewModel
){
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent ={
            ModalDrawerSheet(
                drawerContainerColor = colorResource(R.color.blue_grey),
                modifier = Modifier.width(260.dp)
            ) {
                DrawerContent(authViewModel = authViewModel, navController = navController)
            }
        }
    ){
        Scaffold(
            topBar ={
                HomeHeader(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply {
                                if(isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ){paddingValues ->
            HomeScreenContent(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                event = event,
                state = state
            )
        }
    }

}


@Composable
fun HomeScreenContent(
    modifier: Modifier=Modifier,
    navController: NavController,
    event: (EventHomeScreen)->Unit,
    state: StateHomeScreen
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.mid_night_blue)),
    ) {

        HomeHeader(onOpenDrawer = {})

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
        AppDropDownMenu(menuName = "Number of Questions:", menuList = Constants.numberAsString, text = state.numberOfQuiz.toString(),onDropDownClick = { event(EventHomeScreen.SetNumberOfQuizzes(it.toInt()))})

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Category:", menuList = Constants.categories, text = state.category, onDropDownClick = { event(EventHomeScreen.SetQuizCategory(it))} )

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Difficulty:", menuList = Constants.difficulty, text = state.difficulty,onDropDownClick = { event(EventHomeScreen.SetQuizDifficulty(it)) })

        Spacer(modifier = Modifier.height(Dimens.SmallSpacerHeight))
        AppDropDownMenu(menuName = "Select Type:", menuList = Constants.type, text = state.type,onDropDownClick = { event(EventHomeScreen.SetQuizType(it)) })

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        ButtonBox(text = "Generate Quiz", padding = Dimens.MediumPadding){
            navController.navigate(Routes.QuizScreen.quizParams(state.numberOfQuiz,state.category,state.difficulty,state.type))
        }
    }

}

