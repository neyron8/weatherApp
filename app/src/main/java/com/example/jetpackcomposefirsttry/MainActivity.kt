package com.example.jetpackcomposefirsttry

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposefirsttry.dataClasses.weatherData
import com.example.jetpackcomposefirsttry.logic.getData
import com.example.jetpackcomposefirsttry.screens.MainCard
import com.example.jetpackcomposefirsttry.screens.MyTabLayout
import com.example.jetpackcomposefirsttry.ui.theme.JetpackComposeFirstTryTheme
import com.example.jetpackcomposefirsttry.ui.theme.secondaryBackground

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val daysList = remember {
                mutableStateOf(listOf<weatherData>())
            }
            val currentDay = remember {
                mutableStateOf(weatherData())
            }
            val currentCity = remember {
                mutableStateOf("Paris")
            }

            getData(currentCity.value, daysList, currentDay, this@MainActivity)

            Column(modifier = Modifier.background(secondaryBackground)) {
                MainCard(currentDay, this@MainActivity, currentCity)
                MyTabLayout(daysList, currentDay)
            }

        }
    }
}
