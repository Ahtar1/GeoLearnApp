package com.example.geolearnapp.ui.quiz.multiplechoices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MCBottomSheet(
    viewModel: MultipleChoicesViewModel,
    scope: CoroutineScope = rememberCoroutineScope(),
    sheetState: BottomSheetState
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(topEnd = 64.dp, topStart = 64.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (viewModel.isAnswerCorrectState.collectAsState().value == true) {
                Text(
                    text = "Correct Answer!",
                    color = Color(0xFF50C878),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize()
                        .padding(8.dp)
                )
            } else if (viewModel.isAnswerCorrectState.collectAsState().value == false) {
                Text(
                    text = "Wrong Answer!",
                    color = Color.Red,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize()
                        .padding(8.dp)
                )
            }

            Button(
                onClick = {
                    viewModel.nextQuestion()
                    scope.launch {
                        sheetState.collapse()
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD6EAF8)),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
            ) {
                Text(
                    text = "Next Question",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}