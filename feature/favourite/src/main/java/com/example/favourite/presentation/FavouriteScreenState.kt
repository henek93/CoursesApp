package com.example.favourite.presentation

import com.example.domain.entity.Course

sealed class FavouriteScreenState {

    object Initial : FavouriteScreenState()

    object Loading : FavouriteScreenState()

    data class Success(
        val data: List<Course>
    ) : FavouriteScreenState()

    data class Error(
        val message: String
    ) : FavouriteScreenState()
}