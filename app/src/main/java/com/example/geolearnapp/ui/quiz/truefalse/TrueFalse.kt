package com.example.geolearnapp.ui.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.geolearnapp.ui.quiz.truefalse.BackDialog
import com.example.geolearnapp.ui.quiz.truefalse.BottomSheet
import com.example.geolearnapp.ui.quiz.truefalse.FinishedGameDiolog
import com.example.geolearnapp.ui.quiz.truefalse.TrueFalseViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrueFalseScreen(
    navController: NavHostController
) {

    val viewModel: TrueFalseViewModel = hiltViewModel()

    val dialogState = remember { mutableStateOf(false) }

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheet(
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
            // Harts
            QuizTopCard(
                title = "True False",
                wrongAnswerState = viewModel.wrongAnswerState.collectAsState().value
            )

            // Score
            Text(
                text = "Score:" + viewModel.scoreState.collectAsState().value.toString(),
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

            // Question

            Column(modifier = Modifier.padding(16.dp)) {

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFFEAFAF1),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                        .padding(16.dp)
                ) {

                    val name: String = viewModel.countryState.collectAsState().value.name

                    Text(
                        text = name,
                        color = Color.Black,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    )

                }

                Text(
                    text = "'s capital is",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f)
                )

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color(0xFFEAFAF1),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f)
                        .padding(16.dp)
                ) {

                    val capital: String = viewModel.countryState.collectAsState().value.capital

                    Text(
                        text = capital,
                        fontSize = 32.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(2f)
                        .padding(top = 32.dp)
                ) {
                    Button(
                        onClick = { viewModel.checkAnswer(true); scope.launch { sheetState.expand() } },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .weight(1f),
                        enabled = viewModel.isButtonsActiveState.collectAsState().value,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "True ",
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Check",
                            tint = Color(0xFF50C878),
                            modifier = Modifier
                                .size(48.dp)

                        )
                    }
                    Button(
                        onClick = { viewModel.checkAnswer(false); scope.launch { sheetState.expand() } },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .weight(1f),
                        enabled = viewModel.isButtonsActiveState.collectAsState().value,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "False ",
                            color = Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(48.dp)
                        )
                    }
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
