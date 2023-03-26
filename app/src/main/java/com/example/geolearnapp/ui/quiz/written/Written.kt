package com.example.geolearnapp.ui.quiz

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.geolearnapp.ui.quiz.written.WrittenViewModel

@Composable
fun WrittenScreen() {

    val viewModel: WrittenViewModel = hiltViewModel()

    QuizTopCard(
        title = "Written",
        wrongAnswerState = 0
    )

}

@Preview
@Composable
fun WrittenComposablePreview() {


}