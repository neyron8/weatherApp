package com.example.jetpackcomposefirsttry.logic

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.jetpackcomposefirsttry.dataClasses.weatherData
import org.json.JSONArray
import org.json.JSONObject

fun getData(
    city: String,
    daysList: MutableState<List<weatherData>>,
    currentDay: MutableState<weatherData>,
    context: Context,
) {
    val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
            key +
            "&q=$city" +
            "&days=" +
            "3" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val request = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            val list = getWeatherDays(response)
            daysList.value = list
            currentDay.value = list[0]
        },
        {
            Log.d("RequestError", "$it")
            Toast.makeText(context, "You searching wrong city. Try again", Toast.LENGTH_SHORT).show()
        }
    )
    queue.add(request)
}

fun getWeatherDays(response: String): List<weatherData> {
    if (response.isEmpty()) {
        return listOf()
    }
    val mainObject = JSONObject(response)
    val list = ArrayList<weatherData>()
    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        list.add(
            weatherData(
                city,
                item.getString("date"),
                "",
                item.getJSONObject("day")
                    .getJSONObject("condition").getString("text"),
                item.getJSONObject("day")
                    .getJSONObject("condition").getString("icon"),
                item.getJSONObject("day").getString("mintemp_c"),
                item.getJSONObject("day").getString("maxtemp_c"),
                item.getJSONArray("hour").toString()
            )
        )
    }
    list[0] = list[0].copy(  //Текущая температура есть только у первого дня
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c")
    )
    return list
}

fun getWeatherHours(hours: String): List<weatherData>{
    if (hours.isEmpty()) return listOf()
    val hoursArray = JSONArray(hours)
    val list = ArrayList<weatherData>()
    for (i in 0 until hoursArray.length()){
        val item = hoursArray[i] as JSONObject
        list.add(
            weatherData(
                "",
                item.getString("time"),
                item.getString("temp_c"),
                item.getJSONObject("condition").getString("text"),
                item.getJSONObject("condition").getString("icon"),
                "",
                "",
                ""
            )
        )
    }
    return list
}