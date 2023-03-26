package com.example.geolearnapp.ui.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.geolearnapp.R

@Composable
fun QuizTopCard(
    title: String,
    wrongAnswerState: Int
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp)
            )
            Row {
                Image(
                    painter = if (wrongAnswerState < 3) {
                        painterResource(R.drawable.redheart)
                    } else {
                        painterResource(R.drawable.blackheart)
                    },
                    contentDescription = "Heart",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
                Image(
                    painter = if (wrongAnswerState < 2) {
                        painterResource(R.drawable.redheart)
                    } else {
                        painterResource(R.drawable.blackheart)
                    },
                    contentDescription = "Heart",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
                Image(
                    painter = if (wrongAnswerState < 1) {
                        painterResource(R.drawable.redheart)
                    } else {
                        painterResource(R.drawable.blackheart)
                    },
                    contentDescription = "Heart",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
            }

        }
    }
}
