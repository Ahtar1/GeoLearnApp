package com.example.geolearnapp.ui.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

    val scope = rememberCoroutineScope()

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
        if (viewModel.chosenCountriesSeparatedState.collectAsState().value.isNotEmpty()){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f)
                ) {

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(0)) { viewModel.onCountryClicked(0) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    0
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 0) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(0)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(0),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[0],
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(0)) {
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



                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(1)) { viewModel.onCountryClicked(1) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    1
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 1) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(1)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(1),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[1],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(1)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(2)) { viewModel.onCountryClicked(2) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    2
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 2) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(2)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(2),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[2],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)

                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(2)) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(3)) { viewModel.onCountryClicked(3) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    3
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 3) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(3)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(3),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[3],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)

                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(3)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(4)) { viewModel.onCountryClicked(4) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    4
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 4) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(4)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(4),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[4],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(4)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(5)) { viewModel.onCountryClicked(5) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    5
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 5) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(5)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(5),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[5],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(5)) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(6)) { viewModel.onCountryClicked(6) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    6
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 6) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(6)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(6),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[6],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(6)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(7)) { viewModel.onCountryClicked(7) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    7
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 7) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(7)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(7),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[7],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(7)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(8)) { viewModel.onCountryClicked(8) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    8
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 8) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(8)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(8),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[8],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(8)) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(9)) { viewModel.onCountryClicked(9) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    9
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 9) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(9)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(9),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[9],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(9)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(10)) { viewModel.onCountryClicked(10) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    10
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 10) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(10)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(10),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[10],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)

                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(10)) {
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
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = !viewModel.correctAnswersState.collectAsState().value.contains(11)) { viewModel.onCountryClicked(11) }
                            .background(color = ((if (viewModel.correctAnswersState.collectAsState().value.contains(
                                    11
                                )
                            ) {
                                Color(0xFF50C878)
                            } else if (viewModel.clickedButtonState.collectAsState().value == 11) {
                                Color.Gray
                            } else if(viewModel.wrongAnswersState.collectAsState().value.contains(11)){
                                Color.Red
                            } else {
                                Color.White
                            })))
                    )
                    {
                        androidx.compose.animation.AnimatedVisibility(
                            visible = !viewModel.correctAnswersState.collectAsState().value.contains(11),
                            exit = fadeOut(tween(2000, easing = LinearEasing))
                        ) {
                            Text(
                                text = viewModel.chosenCountriesSeparatedState.collectAsState().value[11],
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            )
                        }
                        if (viewModel.correctAnswersState.collectAsState().value.contains(11)) {
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
        BackDialog(navController=navController,dialogState= dialogState, viewModel = viewModel)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BackDialog(navController: NavHostController, dialogState: MutableState<Boolean>, viewModel: MatchingViewModel) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true,
        )
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Are you sure you want to exit? All progress will be lost.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { dialogState.value = false; navController.navigate("quiz")})
                    {
                        Text("Yes")
                    }
                    Button(onClick = { dialogState.value = false }) {
                        Text("No")
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun MatchingComposablePreview() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        {
            androidx.compose.animation.AnimatedVisibility(
                visible = true,
                exit = fadeOut(tween(2000, easing = LinearEasing))
            ) {
                Text(
                    text = "\nAustria",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                )
            }
        }



        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp))
        {
            androidx.compose.animation.AnimatedVisibility(
                visible = true,
                exit = fadeOut(tween(2000, easing = LinearEasing))
            ) {
                Text(
                    text = "Austria",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .background(color = Color.White)
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        {
            androidx.compose.animation.AnimatedVisibility(
                visible = true,
                exit = fadeOut(tween(2000, easing = LinearEasing))
            ) {
                Text(
                    text = "Austria",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .background(color = Color.White)
                )
            }
        }

    }
}