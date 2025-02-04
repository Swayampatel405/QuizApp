package com.appvantage.quizapp.presentation.authentication.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.appvantage.quizapp.R
import com.appvantage.quizapp.presentation.authentication.AuthState
import com.appvantage.quizapp.presentation.authentication.AuthViewModel

@Composable
fun SignupPage(
    modifier: Modifier =Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home_screen")
            is AuthState.Error -> Toast.makeText(context,(authState.value as AuthState.Error).message,
                Toast.LENGTH_LONG).show()
            else-> Unit
        }
    }


    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Sign Up",
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(R.color.blue_grey)
        )

        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            singleLine = true,
            onValueChange = { email = it},
            label = {
                Text("Email")
            },
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.blue_grey),
                unfocusedContainerColor = colorResource(R.color.white),
                focusedBorderColor = colorResource(R.color.black),
                unfocusedBorderColor = colorResource(R.color.blue_grey),
                focusedLabelColor = colorResource(R.color.splash),
                unfocusedLabelColor = colorResource(R.color.splash)
            )
        )
        Spacer(modifier=Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            singleLine = true,
            label = {
                Text("Password")
            },
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.blue_grey),
                unfocusedContainerColor = colorResource(R.color.white),
                focusedBorderColor = colorResource(R.color.black),
                unfocusedBorderColor = colorResource(R.color.blue_grey),
                focusedLabelColor = colorResource(R.color.splash),
                unfocusedLabelColor = colorResource(R.color.splash)
            )
        )
        Spacer(modifier=Modifier.height(26.dp))
        Button(
            modifier = Modifier.height(50.dp).width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.splash),
                contentColor = colorResource(R.color.black)
            ),
            onClick = {
                authViewModel.signup(email, password)
            },
            enabled = authState.value != AuthState.Loading
        ){
            Text("Create User", fontSize = 20.sp)
        }

        Spacer(modifier=Modifier.height(8.dp))

        TextButton(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text(
                text = "Already have an account ? Login ",
                fontSize = 15.sp,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(R.color.blue_grey)
            )
        }
    }

}