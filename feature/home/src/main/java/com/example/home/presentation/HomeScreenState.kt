package com.example.home.presentation

import com.example.domain.entity.Course

sealed class HomeScreenState {

    object Initial : HomeScreenState()

    object Loading : HomeScreenState()

    data class Succsed(
        val list: List<Course>
    ) : HomeScreenState()

    data class Error(
        val message: String
    ) : HomeScreenState()
}