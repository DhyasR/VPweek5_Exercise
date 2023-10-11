package com.example.vpweek5_exercise.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vpweek5_exercise.model.GameState
import com.example.vpweek5_exercise.model.GuessingGameModel

class GuessingGameViewModel : ViewModel() {
    private val model = GuessingGameModel((1..10).random())

    fun guessNumber(number: Int) {
        if (number == model.targetNumber) {
            model.gameState = GameState.WIN
        } else {
            model.gameState = GameState.LOSE
        }
    }

    fun getRandom(): GuessingGameModel {
        return model
    }

    fun getModel(): Int {
        return model.targetNumber
    }
}