package com.example.geolearnapp.ui.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.geolearnapp.ui.quiz.truefalse.FinishedGameDiolog
import com.example.geolearnapp.ui.quiz.truefalse.TrueFalseViewModel

@Composable
fun TrueFalseScreen(
    navController: NavHostController
) {

    val viewModel: TrueFalseViewModel = hiltViewModel()

    Column {

        Row {
            Text(
                text = "True False",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Image(
                painter = if(viewModel.wrongAnswerState.collectAsState().value<3){painterResource(com.example.geolearnapp.R.drawable.redheart)}
                else{painterResource(com.example.geolearnapp.R.drawable.blackheart)},
                contentDescription = "Heart",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Image(
                painter = if(viewModel.wrongAnswerState.collectAsState().value<2){painterResource(com.example.geolearnapp.R.drawable.redheart)}
                else{painterResource(com.example.geolearnapp.R.drawable.blackheart)},
                contentDescription = "Heart",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Image(
                painter = if(viewModel.wrongAnswerState.collectAsState().value<1){painterResource(com.example.geolearnapp.R.drawable.redheart)}
                else{painterResource(com.example.geolearnapp.R.drawable.blackheart)},
                contentDescription = "Heart",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
        }



        Row {

            val name:String = viewModel.countryState.collectAsState().value.name
            val capital:String = viewModel.countryState.collectAsState().value.capital
            Text(
                text = name,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Text(
                text = capital,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
        }
        Row {
            Button(
                onClick = { viewModel.checkAnswer(true) },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .weight(1f),
                enabled = viewModel.isButtonsActiveState.collectAsState().value
            ) {
                Text(
                    text = "True",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Button(
                onClick = { viewModel.checkAnswer(false) },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .weight(1f),
                enabled = viewModel.isButtonsActiveState.collectAsState().value
            ) {
                Text(
                    text = "False",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        if (viewModel.congraState.collectAsState().value == true) {
            Text(
                text = "Correct",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
        } else if (viewModel.congraState.collectAsState().value == false) {
            Text(
                text = "Incorrect",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
        }
        if (viewModel.congraState.collectAsState().value!=null){
            Button(
                onClick = { viewModel.nextQuestion() },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = "Next",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Text(
            text = viewModel.scoreState.collectAsState().value.toString(),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        )
    }

    if(viewModel.isGameFinishedState.collectAsState().value){
        FinishedGameDiolog(
            score = viewModel.scoreState.collectAsState().value,
            onGoToMenu = { viewModel.onGoToMenu(navController) },
            onPlayAgain = { viewModel.onPlayAgain() }
        )
    }

}

@Preview
@Composable
fun SimpeComposablePreview() {

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            Text(
                text = "True False",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Icon(
                imageVector = Icons.Default.LocationCity,
                contentDescription = "Heart",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Icon(
                imageVector = Icons.Filled.LocationCity,
                contentDescription = "Heart",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Icon(
                imageVector = Icons.Outlined.LocationCity,
                contentDescription = "Heart",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
        }

        Row {
            Text(
                text = "name",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Text(
                text = "capital",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
        }
        Row {
            Button(
                onClick = {  },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = "True",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
            Button(
                onClick = {  },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = "False",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}