package com.example.geolearnapp.ui.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

    Column(modifier = Modifier.fillMaxSize().padding(start=8.dp,end=8.dp,top=16.dp,bottom=16.dp)) {
        Row(modifier = Modifier.weight(1f)) {

            Button(
                onClick =  {navController.navigate("multipleChoices")},
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

@Composable
fun NavGraphBuilder.quizGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "quiz") {

        composable("multipleChoices") {
            MultipleChoicesScreen()
        }
    }
}


@Preview
@Composable
fun SimpleComposablePreview() {

    Column(
        modifier = Modifier.fillMaxSize().navigationBarsPadding().padding(16.dp, bottom = 36.dp)
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(text = "Multiple Choice")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(text = "True False")
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(text = "")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(text = "Multiple Choice")
            }
        }
    }


}