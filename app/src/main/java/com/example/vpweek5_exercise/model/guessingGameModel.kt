package com.example.vpweek5_exercise.model

data class GuessingGameModel(
    var targetNumber: Int,
    var attempts: Int = 0,
    var gameState: GameState = GameState.IN_PROGRESS,
    var points: Int = 0
)

enum class GameState {
    IN_PROGRESS,
    WIN,
    LOSE,
    LOSE_OUT_OF_ATTEMPTS
}