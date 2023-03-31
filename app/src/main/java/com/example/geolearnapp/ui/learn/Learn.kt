package com.example.geolearnapp.ui.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.geolearnapp.data.database.entity.Country

@Composable
fun LearnScreen() {

    val learnViewModel: LearnViewModel = hiltViewModel()
    val state by learnViewModel.state.collectAsState()

    if (state.isEmpty()) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
                    .wrapContentSize(Alignment.Center),
            )
        }

    }

    LazyColumn {
        item{
            Box(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = Color.White)
                    .border(
                        width = 0.5.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(6.dp)
                    )
            ){
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp)
                ) {
                    Text(
                        text = "Country",
                        fontSize = 24.sp,
                        color= Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .weight(4f)
                    )

                    Text(
                        text = "|",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(2f),
                        color = Color.Black
                    )

                    Text(
                        text = "Capital",
                        color= Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .weight(4f)
                    )

                }
            }
        }
        items(state) { country: Country ->
            val color =
                if (state.indexOf(country) % 2 == 0) Color(0xFFD6EAF8) else Color(0xFFEAFAF1)
            Box(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = color)
                    .border(
                        width = 0.2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    )

            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(top = 12.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = country.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(4f),
                        color = Color.Black,
                    )
                    Text(
                        text = ":",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(2f),
                        color = Color.Black
                    )
                    Text(
                        text = if (country.capital == "") "None" else country.capital,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(4f),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {

    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)
            .border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(6.dp)
            )
    ){
        Row{
            Text(
                text = "Country",
                fontSize = 24.sp,
                color= Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .weight(4f)

            )

            Text(
                text = "|",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(2f),
                color = Color.Black
            )

            Text(
                text = "Capital",
                color= Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .weight(4f)

            )

        }
    }


}

