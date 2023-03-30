package com.example.geolearnapp.ui.quiz.truefalse

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FinishedGameDiolog(
    onPlayAgain: () -> Unit,
    onGoToMenu: () -> Unit,
    score: String,
    highScore: String
) {
    Dialog(
        onDismissRequest = { onGoToMenu() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = true
        )
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Game Over",
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Text(
                    text = "Your Score: $score ",
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Text(
                    text = "High Score: $highScore",
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                Row {
                    Button(
                        onClick = { onPlayAgain() },
                        modifier = androidx.compose.ui.Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    ) {
                        Text(text = "Play Again", textAlign = TextAlign.Center)
                    }
                    Button(
                        onClick = { onGoToMenu() },
                        modifier = androidx.compose.ui.Modifier
                            .wrapContentSize()
                            .padding(16.dp)
                    ) {
                        Text(text = "Go To Menu", textAlign = TextAlign.Center)
                    }

                }
            }
        }
    }
}