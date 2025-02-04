package com.appvantage.quizapp.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.appvantage.quizapp.R
import com.appvantage.quizapp.presentation.authentication.AuthState
import com.appvantage.quizapp.presentation.authentication.AuthViewModel
import com.appvantage.quizapp.presentation.util.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(
    onOpenDrawer:()->Unit
){

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.quiz_app),
                color = colorResource(R.color.blue_grey),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 50.dp),
                fontSize = Dimens.MediumTextSize,

            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .padding(start = 8.dp, end = 8.dp).clickable {
                    onOpenDrawer()
                }
            )
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_account_box_24),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp).weight(1f),
                    tint = colorResource(R.color.blue_grey)
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = colorResource(R.color.dark_slate_blue),
            titleContentColor = colorResource(R.color.blue_grey),
            navigationIconContentColor = colorResource(R.color.blue_grey),
            actionIconContentColor = colorResource(R.color.blue_grey),
            scrolledContainerColor = colorResource(R.color.blue_grey)
        )

    )
}

@Composable
fun DrawerContent(modifier: Modifier= Modifier,navController: NavController,authViewModel: AuthViewModel){

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else-> Unit
        }
    }


    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Quiz App", fontSize = 30.sp, modifier = Modifier.padding(start = 46.dp), color = colorResource(R.color.mid_night_blue))

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.VerySmallViewHeight)
                .clip(RoundedCornerShape(Dimens.MediumCornerRadius))
                .background(color = colorResource(R.color.mid_night_blue))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    authViewModel.signOut()
                }
                .padding(24.dp)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text("Log Out", fontSize = 22.sp, color = colorResource(R.color.dark_slate_blue))

            Icon(painterResource(R.drawable.baseline_logout_24), contentDescription = "logout")

        }

    }

}
