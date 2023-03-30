package com.example.geolearnapp.ui.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun QuizScreen(navController: NavHostController) {
    println("quiz screen")

    Column(modifier = Modifier.fillMaxSize().padding(start=8.dp,end=8.dp,top=16.dp,bottom=16.dp)) {
        Row(modifier = Modifier.weight(1f)) {

            Button(
                onClick =  {navController.navigate("multipleChoices")},
                elevation = ButtonDefaults.elevation(6.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = "Multiple Choice",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Button(
                onClick = { navController.navigate("trueFalse") },
                elevation = ButtonDefaults.elevation(6.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = "True False",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Button(
                onClick = { navController.navigate("written") },
                elevation = ButtonDefaults.elevation(6.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = "Written",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Button(
                onClick = { navController.navigate("matching") },
                elevation = ButtonDefaults.elevation(6.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = "Matching",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
