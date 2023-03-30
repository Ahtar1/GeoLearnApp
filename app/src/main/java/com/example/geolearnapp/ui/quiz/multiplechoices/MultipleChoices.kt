package com.example.geolearnapp.ui.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.geolearnapp.ui.quiz.multiplechoices.MCBottomSheet
import com.example.geolearnapp.ui.quiz.multiplechoices.MultipleChoicesViewModel
import com.example.geolearnapp.ui.quiz.truefalse.BackDialog
import com.example.geolearnapp.ui.quiz.truefalse.BottomSheet
import com.example.geolearnapp.ui.quiz.truefalse.FinishedGameDiolog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MultipleChoicesScreen(
    navController: NavHostController
) {

    val viewModel: MultipleChoicesViewModel = hiltViewModel()

    val dialogState = remember { mutableStateOf(false) }

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            MCBottomSheet(
                viewModel = viewModel,
                scope = scope,
                sheetState = sheetState,
            )
        },
        sheetPeekHeight = 0.dp,
        sheetGesturesEnabled = false,
        sheetShape = RoundedCornerShape(topEnd = 64.dp, topStart = 64.dp)
    ){
        Column {

            QuizTopCard(
                title = "Multiple Choices",
                wrongAnswerState = viewModel.wrongAnswerState.collectAsState().value,
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Score: ${viewModel.scoreState.collectAsState().value}",
                    fontSize = 30.sp,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                Text(
                    text = "${viewModel.chosenCapitalState.collectAsState().value} is the capital of which country?",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 32.dp, bottom = 48.dp)
                )

                Button(
                    onClick = { viewModel.checkAnswer(0); scope.launch { sheetState.expand() } },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFD6EAF8),
                        disabledBackgroundColor = if (viewModel.correctAnswerState.collectAsState().value == viewModel.optionsState.collectAsState().value[0]) Color(0xFF50C878)
                        else if(viewModel.selectedAnswerState.collectAsState().value== 0) Color.Red
                        else Color.LightGray
                    ),
                    enabled = viewModel.isButtonsActiveState.collectAsState().value,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Text(
                        text = viewModel.optionsState.collectAsState().value[0],
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }

                Button(
                    onClick = { viewModel.checkAnswer(1); scope.launch { sheetState.expand() } },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFD6EAF8),
                        disabledBackgroundColor = if (viewModel.correctAnswerState.collectAsState().value == viewModel.optionsState.collectAsState().value[1]) Color(0xFF50C878)
                        else if(viewModel.selectedAnswerState.collectAsState().value== 1) Color.Red
                        else Color.LightGray
                    ),
                    enabled = viewModel.isButtonsActiveState.collectAsState().value,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Text(
                        text = viewModel.optionsState.collectAsState().value[1],
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }

                Button(
                    onClick = { viewModel.checkAnswer(2); scope.launch { sheetState.expand() } },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFD6EAF8),
                        disabledBackgroundColor = if (viewModel.correctAnswerState.collectAsState().value == viewModel.optionsState.collectAsState().value[2]) Color(0xFF50C878)
                        else if(viewModel.selectedAnswerState.collectAsState().value== 2) Color.Red
                        else Color.LightGray
                    ),
                    enabled = viewModel.isButtonsActiveState.collectAsState().value,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Text(
                        text = viewModel.optionsState.collectAsState().value[2],
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }

                Button(
                    onClick = { viewModel.checkAnswer(3); scope.launch { sheetState.expand() } },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFD6EAF8),
                        disabledBackgroundColor = if (viewModel.correctAnswerState.collectAsState().value == viewModel.optionsState.collectAsState().value[3]) Color(0xFF50C878)
                        else if(viewModel.selectedAnswerState.collectAsState().value== 3) Color.Red
                        else Color.LightGray
                    ),
                    enabled = viewModel.isButtonsActiveState.collectAsState().value,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Text(
                        text = viewModel.optionsState.collectAsState().value[3],
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }

        if (viewModel.isGameFinishedState.collectAsState().value) {
            FinishedGameDiolog(
                highScore = viewModel.highScoreState.collectAsState().value.toString(),
                score = viewModel.scoreState.collectAsState().value.toString(),
                onGoToMenu = { viewModel.onGoToMenu(navController) },
                onPlayAgain = { viewModel.onPlayAgain(); scope.launch { sheetState.collapse() }}
            )
        }

        BackHandler(enabled = true) {
            dialogState.value = true
        }

        if (dialogState.value) {
            BackDialog(navController, dialogState)
        }
    }


}