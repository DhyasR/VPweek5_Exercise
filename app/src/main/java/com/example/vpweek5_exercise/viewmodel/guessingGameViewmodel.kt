package com.example.vpweek5_exercise.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vpweek5_exercise.model.GameState
import com.example.vpweek5_exercise.model.GuessingGameModel

class GuessingGameViewModel : ViewModel() {
    private var model = GuessingGameModel(getModel())

    fun guessNumber(number: Int) {
        if (number == model.targetNumber) {
            model.gameState = GameState.WIN
        } else {
            model.gameState = GameState.LOSE
        }
    }

    fun getFill(): GuessingGameModel {
        return model
    }

    fun getModel(): Int {
        val newModel = GuessingGameModel((1..10).random())
        return newModel.targetNumber
    }
}