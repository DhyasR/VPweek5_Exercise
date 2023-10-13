package com.example.vpweek5_exercise.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Soal2View() {
    var inputSKS by rememberSaveable { mutableStateOf("") }
    var inputScore by rememberSaveable { mutableStateOf("") }
    var inputName by rememberSaveable { mutableStateOf("") }

    Surface(modifier = Modifier.padding(16.dp)) {
        Column() {
            Text(
                text = "Courses",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold
            )

            Text(text = "Total SKS : ", fontSize = 20.sp)
            Text(text = "IPK       : ", fontSize = 20.sp)

            Column() {
                Row {
                    CustomTextField(
                        value = inputSKS,
                        onValueChanged = { inputSKS = it },
                        text = "SKS",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp)
                    )

                    CustomTextField(
                        value = inputScore,
                        onValueChanged = { inputSKS = it },
                        text = "Score",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 5.dp)
                    )
                }

                Row {
                    CustomTextField(
                        value = inputName,
                        onValueChanged = { inputSKS = it },
                        text = "Name",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.width(250.dp)
                    )

                    Button(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(start = 15.dp, top = 10.dp)
                            .height(60.dp)
                            .width(20.dp)
                    ) {
                        Text("+")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2View()
}