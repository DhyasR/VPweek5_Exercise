package com.example.vpweek5_exercise.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vpweek5_exercise.model.GuessingGameModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GuessingGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GuessingGameModel(getModel()))
    val uiState: StateFlow<GuessingGameModel> = _uiState.asStateFlow()

    fun guessNumber(number: Int) {
        if (number == uiState.value.targetNumber) {
            uiState.value.points += 1
            uiState.value.attempts = 0

            _uiState.value = uiState.value.copy(targetNumber = (1..10).random())
        } else {
            uiState.value.attempts += 1
        }
    }

    fun getFill(): GuessingGameModel {
        return uiState.value
    }

    fun getModel(): Int {
        val newModel = GuessingGameModel((1..10).random())
        return newModel.targetNumber
    }
}