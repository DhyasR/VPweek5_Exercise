package com.example.vpweek5_exercise.ui.view

import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vpweek5_exercise.model.GameState
import com.example.vpweek5_exercise.viewmodel.GuessingGameViewModel

@Composable
fun Soal1View() {
    val viewModel: GuessingGameViewModel = viewModel()
    var inputValue by rememberSaveable { mutableStateOf("") }
    var isDialogVisible by rememberSaveable { mutableStateOf(false) }

    Surface(modifier = Modifier.padding(16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Guess The Number",
                fontSize = 34.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Box(
                modifier = Modifier
                    .background(color = Color.LightGray)
                    .clip(shape = RoundedCornerShape(25.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = Color(rgb(69, 86, 184)))
                                .clip(RoundedCornerShape(50.dp))
                        ) {
                            Text(
                                text = "Number of Guesses : ${viewModel.getFill().attempts}",
                                color = Color.White,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                            )
                        }
                    }

                    Row {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = viewModel.getFill().targetNumber.toString(),
                                fontSize = 32.sp,
                                modifier = Modifier.padding(bottom = 15.dp)
                            )

                            Text(
                                text = "From 1 to 10 Guess the Number",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )

                            Text(text = "Score : ${viewModel.getFill().points}", fontSize = 16.sp)

                            CustomTextField(
                                value = inputValue,
                                onValueChanged = { inputValue = it },
                                text = "Enter Your number",
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )

                            Button(
                                onClick = {
                                    if (inputValue.isNotBlank() && viewModel.getFill().gameState != GameState.LOSE_OUT_OF_ATTEMPTS) {
                                        viewModel.guessNumber(inputValue.toInt())

                                        when (viewModel.getFill().gameState) {
                                            GameState.WIN -> {
                                                viewModel.getFill().points += 1
                                                viewModel.getFill().targetNumber =
                                                    viewModel.getModel()
                                                viewModel.getFill().attempts = 0
                                            }

                                            GameState.LOSE -> {
                                                viewModel.getFill().attempts += 1

                                                if (viewModel.getFill().attempts >= 3) {
                                                    viewModel.getFill().gameState =
                                                        GameState.LOSE_OUT_OF_ATTEMPTS
                                                    isDialogVisible = true
                                                }
                                            }

                                            else -> {}
                                        }

                                        inputValue = ""
                                    }
                                },
                                modifier = Modifier
                                    .padding(top = 10.dp),
                                colors = ButtonDefaults.buttonColors(Color(rgb(69, 86, 184)))
                            ) {
                                Text(text = "Submit")
                            }

                            if (isDialogVisible) {
                                AlertDialog(
                                    onDismissRequest = { isDialogVisible = false },
                                    title = { Text("Welp!") },
                                    text = { Text("Your Score : ${viewModel.getFill().points}") },
                                    confirmButton = {
                                        Button(
                                            onClick = {
                                                isDialogVisible = false
                                                viewModel.getFill().attempts = 0
                                            }
                                        ) {
                                            Text("OK")
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text) },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListMoviePreview() {
    Soal1View()
}