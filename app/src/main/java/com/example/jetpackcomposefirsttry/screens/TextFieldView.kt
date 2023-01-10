package com.example.jetpackcomposefirsttry.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposefirsttry.R
import com.example.jetpackcomposefirsttry.ui.theme.White
import com.example.jetpackcomposefirsttry.ui.theme.primaryBackground
import com.example.jetpackcomposefirsttry.ui.theme.primaryText
import com.example.jetpackcomposefirsttry.ui.theme.secondaryText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldView(currentCity: MutableState<String>) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val textState = remember { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxWidth()
            .defaultMinSize(minHeight = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            textState.value,
            onValueChange = {
                textState.value = it
            },
            singleLine = true,
            modifier = Modifier.border(
                width = 2.dp,
                color = secondaryText,
                shape = RoundedCornerShape(25.dp)
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    currentCity.value = textState.value
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            label = {
                Text(text = "City", color = secondaryText)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.city_24),
                    tint = secondaryText,
                    contentDescription = "textField"
                )
            },
            shape = RoundedCornerShape(25.dp)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview(showBackground = true)
fun TextFieldViewPreview() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val textState = remember { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            textState.value,
            onValueChange = {
                textState.value = it
            },
            modifier = Modifier.border(width = 2.dp, color = primaryText),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    //currentCity.value = textState.value
                    keyboardController?.hide()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                backgroundColor = primaryBackground
            ),
            label = {
                Text(text = "City")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.city_24),
                    contentDescription = "textField"
                )
            },
            shape = RoundedCornerShape(25.dp)

        )
    }
}