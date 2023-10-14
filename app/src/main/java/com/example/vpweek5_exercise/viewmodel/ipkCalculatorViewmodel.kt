package com.example.vpweek5_exercise.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.vpweek5_exercise.model.ipkCalculator

class ipkCalculatorViewmodel : ViewModel() {
    private val _courseList = mutableStateListOf<ipkCalculator>()
    val courseList: List<ipkCalculator> get() = _courseList

    fun passDataUser(sks: Int, score: Double, name: String) {
        val dataUser = ipkCalculator(sks, score, name)
        _courseList.add(dataUser)
    }

    fun getTotalSKS(): Int {
        var totalSKS = 0
        for (course in _courseList) {
            totalSKS += course.sks
        }

        return totalSKS
    }

    fun getAverageScore(): String {
        if (_courseList.isEmpty()) {
            return "0.0"
        }

        var totalScore = 0.0

        for (course in _courseList) {
            totalScore += (course.score * course.sks)
        }

        val averageScore = totalScore / getTotalSKS()
        return String.format("%.2f", averageScore)
    }

    fun deleteCourse(course: ipkCalculator) {
        _courseList.remove(course)
    }
}