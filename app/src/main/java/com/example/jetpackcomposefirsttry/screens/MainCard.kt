package com.example.jetpackcomposefirsttry.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcomposefirsttry.R
import com.example.jetpackcomposefirsttry.dataClasses.weatherData
import com.example.jetpackcomposefirsttry.ui.theme.*

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainCard(
    currentDay: MutableState<weatherData>,
    context: Context,
    currentCity: MutableState<String>
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        var visible by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.5f),
            backgroundColor = primaryBackground,
            elevation = 0.5.dp,
            shape = RoundedCornerShape(14.dp)
        ) {
            gifBack()
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Last update's time: ",
                        modifier = Modifier.padding(5.dp),
                        style = TextStyle(fontSize = 15.sp),
                        color = primaryText
                    )
                    Text(
                        text = currentDay.value.time,
                        modifier = Modifier.padding(5.dp),
                        style = TextStyle(fontSize = 15.sp),
                        color = primaryText
                    )
                    AsyncImage(
                        model = "https:${currentDay.value.weatherIcon}",
                        contentDescription = "sa", modifier = Modifier
                            .size(34.dp)
                            .padding(5.dp)
                    )
                }
                Text(
                    text = currentDay.value.city,
                    style = TextStyle(fontSize = 25.sp),
                    color = primaryText
                )
                Text(
                    text = if (currentDay.value.currentTemp.isNotEmpty())
                        currentDay.value.currentTemp + "ºC"
                    else currentDay.value.minTemp +
                            "ºC/${currentDay.value.maxTemp}ºC",
                    style = TextStyle(fontSize = 40.sp),
                    color = primaryText
                )
                Text(
                    text = currentDay.value.condition,
                    style = TextStyle(fontSize = 17.sp),
                    color = secondaryText
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        visible = !visible
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "search",
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_sync_24),
                            contentDescription = "sync"
                        )
                    }
                }

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(animationSpec = tween(500)),
                    exit = fadeOut(animationSpec = tween(500))
                ) {
                    TextFieldView(currentCity)
                }
            }

        }
    }
}
