package com.example.jetpackcomposefirsttry.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.jetpackcomposefirsttry.dataClasses.weatherData

@Composable
fun MainList(list: List<weatherData>, currentDay: MutableState<weatherData>){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(
            list
        ){
                _, item-> ListCard(item, currentDay)
        }
    }
}