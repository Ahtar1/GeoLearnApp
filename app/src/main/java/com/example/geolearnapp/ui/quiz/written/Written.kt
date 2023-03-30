package com.example.geolearnapp.ui.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.geolearnapp.ui.quiz.truefalse.FinishedGameDiolog
import com.example.geolearnapp.ui.quiz.written.WrittenBottomSheet
import com.example.geolearnapp.ui.quiz.written.WrittenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun WrittenScreen(
    navController: NavHostController
) {

    val viewModel: WrittenViewModel = hiltViewModel()

    val dialogState = remember { mutableStateOf(false) }

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val scope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            WrittenBottomSheet(
                viewModel = viewModel,
                scope = scope,
                sheetState = sheetState,
            )
        },
        sheetPeekHeight = 0.dp,
        sheetGesturesEnabled = false,
        sheetShape = RoundedCornerShape(topEnd = 64.dp, topStart = 64.dp),
    ) {
        Column {
            QuizTopCard(
                title = "Written",
                wrongAnswerState = viewModel.wrongAnswerState.collectAsState().value
            )

            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 48.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp
            ) {
                Text(
                    text = viewModel.chosenCountryState.collectAsState().value.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                )
            }
            var text by remember { mutableStateOf("") }
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 8.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        label = { Text(text = "Capital") },
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    )

                    Row {
                        Button(
                            onClick = {
                                viewModel.checkAnswer(text)
                                text = ""
                                keyboardController?.hide()
                                scope.launch { sheetState.expand() }
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(16.dp)
                        ) {
                            Text(text = "Check")
                        }

                        TextButton(
                            onClick = {
                                viewModel.checkAnswer(""); viewModel.nextQuestion()
                            },
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "I don't know"
                            )
                        }

                    }


                }

            }


        }
    }
    if (viewModel.isGameFinishedState.collectAsState().value) {
        FinishedGameDiolog(
            highScore = viewModel.highScoreState.collectAsState().value.toString(),
            score = viewModel.scoreState.collectAsState().value.toString(),
            onGoToMenu = { viewModel.onGoToMenu(navController) },
            onPlayAgain = { viewModel.onPlayAgain(); scope.launch { sheetState.collapse() } }
        )
    }

    BackHandler(enabled = true) {
        dialogState.value = true
    }

    if (dialogState.value) {
        com.example.geolearnapp.ui.quiz.truefalse.BackDialog(navController, dialogState)
    }

}
