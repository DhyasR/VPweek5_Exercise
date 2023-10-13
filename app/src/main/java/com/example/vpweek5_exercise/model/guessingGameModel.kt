package com.example.vpweek5_exercise.model

data class GuessingGameModel(
    var targetNumber: Int,
    var attempts: Int = 0,
    var isWin: Boolean = false,
    var points: Int = 0
)