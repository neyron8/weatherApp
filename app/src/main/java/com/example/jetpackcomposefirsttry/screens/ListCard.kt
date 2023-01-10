package com.example.jetpackcomposefirsttry.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcomposefirsttry.dataClasses.weatherData
import com.example.jetpackcomposefirsttry.ui.theme.primaryBackground
import com.example.jetpackcomposefirsttry.ui.theme.primaryText
import com.example.jetpackcomposefirsttry.ui.theme.secondaryText

@Composable
fun ListCard(item: weatherData, currentDay: MutableState<weatherData>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 3.dp)
            .clickable {
                if (item.hours.isEmpty()) return@clickable // чтобы не работало нажатие
                // по часам, т.к из за этого получается баг с отсутсвием часов и города
                currentDay.value = item
            },
        backgroundColor = primaryBackground,
        elevation = 5.dp,
        //shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, secondaryText),
                    shape = RoundedCornerShape(5.dp)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )
            ) {
                Text(
                    text = item.time,
                    color = primaryText
                )
                Text(
                    text = item.condition,
                    color = primaryText
                )
            }
            Text(
                text = item.currentTemp.ifEmpty { "${item.minTemp + "°C"}/${item.maxTemp + "° C"}" },
                color = primaryText,
                style = TextStyle(fontSize = 25.sp)
            )
            AsyncImage(
                model = "https:${item.weatherIcon}",
                contentDescription = "weather",
                modifier = Modifier
                    .size(34.dp)
                    .padding(end = 8.dp)
            )
        }
    }
}