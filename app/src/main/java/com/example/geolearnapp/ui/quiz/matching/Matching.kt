package com.example.geolearnapp.ui.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.geolearnapp.ui.quiz.matching.MatchingViewModel
import com.example.geolearnapp.ui.quiz.truefalse.BackDialog
import com.example.geolearnapp.ui.quiz.truefalse.FinishedGameDiolog
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@Composable
fun MatchingScreen(
    navController: NavHostController
) {

    val viewModel: MatchingViewModel = hiltViewModel()

    val dialogState = remember { mutableStateOf(false) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "Matching",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                )

                Text(
                    text = "Time: ${if(viewModel.timeState.collectAsState().value==-0.0) "--" else DecimalFormat("#.##").format(viewModel.timeState.collectAsState().value)}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                )
            }
        }


            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.SpaceBetween,
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(12) { index ->
                    Box(
                        contentAlignment = Alignment.Center,
                        propagateMinConstraints = false,
                        modifier = Modifier
                            .aspectRatio(0.70f)
                            .padding(8.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(index)) { viewModel.onCountryClicked(index) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    index
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == index) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(index)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(index),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[index],
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(index)) {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Filled.Check,
                                contentDescription = "Correct",
                                tint = Color.White,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }


    }
    if (viewModel.isGameFinishedState.collectAsState().value){
        FinishedGameDiolog(
            score = DecimalFormat("#.##").format(viewModel.timeState.collectAsState().value).toString(),
            highScore = "${viewModel.highScoreState.collectAsState().value},${viewModel.highScoreDecimalState.collectAsState().value}",
            onGoToMenu = { viewModel.onGoToMenu(navController) },
            onPlayAgain = { viewModel.onPlayAgain() },
        )
    }

    BackHandler(enabled = true) {
        dialogState.value = true
    }

    if (dialogState.value) {
        BackDialog(navController=navController,dialogState= dialogState)
    }
}