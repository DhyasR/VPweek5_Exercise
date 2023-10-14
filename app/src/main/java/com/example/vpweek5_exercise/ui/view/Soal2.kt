package com.example.vpweek5_exercise.ui.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vpweek5_exercise.model.ipkCalculator
import com.example.vpweek5_exercise.viewmodel.ipkCalculatorViewmodel

@Composable
fun Soal2View(viewModel: ipkCalculatorViewmodel = viewModel()) {
    Surface(modifier = Modifier.padding(16.dp)) {
        LazyColumn {
            item { MainUI() }

            items(viewModel.courseList) { course ->
                SubjectCard(course)
            }
        }
    }
}

@Composable
fun MainUI() {
    val context = LocalContext.current
    val viewModel: ipkCalculatorViewmodel = viewModel()
    var inputSKS by rememberSaveable { mutableStateOf("") }
    var inputScore by rememberSaveable { mutableStateOf("") }
    var inputName by rememberSaveable { mutableStateOf("") }

    Column {
        Text(
            text = "Courses",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Total SKS : ${viewModel.getTotalSKS()}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = "IPK       : ${viewModel.getAverageScore()}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 5.dp)
        )

        Column(modifier = Modifier.padding(top = 5.dp)) {
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
                    onValueChanged = { inputScore = it },
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

            Row(modifier = Modifier.fillMaxWidth()) {
                CustomTextField(
                    value = inputName,
                    onValueChanged = { inputName = it },
                    text = "Name",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.width(250.dp)
                )

                Button(
                    onClick = {
                        try {
                            val sks = inputSKS.toInt()
                            val score = inputScore.toDouble()
                            viewModel.passDataUser(sks, score, inputName)

                            inputSKS = ""
                            inputScore = ""
                            inputName = ""
                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                context,
                                "Invalid input, please try again!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .padding(start = 15.dp, top = 5.dp)
                        .height(60.dp)
                        .width(100.dp),
                    enabled = inputSKS.isNotBlank() && inputScore.isNotBlank() && inputName.isNotBlank()
                ) {
                    Text("+", fontSize = 24.sp)
                }
            }
        }
    }
}

@Composable
fun SubjectCard(course: ipkCalculator) {
    val viewModel: ipkCalculatorViewmodel = viewModel()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Name  : ${course.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(text = "SKS   : ${course.sks}", fontSize = 16.sp)

                Text(text = "Score : ${course.score}", fontSize = 16.sp)
            }

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Button",
                tint = Color.Red,
                modifier = Modifier.clickable {
                    viewModel.deleteCourse(course)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2View()
}