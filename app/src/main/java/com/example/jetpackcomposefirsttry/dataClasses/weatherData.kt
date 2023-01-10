package com.example.jetpackcomposefirsttry.dataClasses

data class weatherData(
    val city: String = "0",
    val time: String = "0",
    val currentTemp: String = "0",
    val condition: String = "0",
    val weatherIcon: String = "0",
    val minTemp: String = "0",
    val maxTemp: String = "0",
    val hours: String = ""
)
