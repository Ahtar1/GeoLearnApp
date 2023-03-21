package com.example.geolearnapp.ui.quiz.truefalse

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
fun BottomSheet(
    viewModel: TrueFalseViewModel,
    scope: CoroutineScope = rememberCoroutineScope(),
    sheetState: BottomSheetState,) {

    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        // Congratulation
        Column(
            modifier = Modifier.height(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.congraState.collectAsState().value == true) {
                Text(
                    text = "Congratulations!",
                    color = Color(0xFF50C878),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize()
                        .padding(8.dp)
                )
            } else if (viewModel.congraState.collectAsState().value == false) {
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

            // True Answer
            println(viewModel.trueCapitalState.collectAsState().value)
            Text(
                text = "The Capital of ${viewModel.countryState.collectAsState().value.name} is ${viewModel.trueCapitalState.collectAsState().value}",
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize()
                    .padding(8.dp)
            )

            // Next Button

            if (viewModel.congraState.collectAsState().value != null) {
                Button(
                    onClick = { viewModel.nextQuestion(); scope.launch { sheetState.collapse() } },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                ) {
                    Text(
                        text = "Next",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}