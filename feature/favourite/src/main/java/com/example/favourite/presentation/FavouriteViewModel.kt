package com.example.favourite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favourite.domain.FavouriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: FavouriteRepository
) : ViewModel() {
    private val _screenState = MutableStateFlow<FavouriteScreenState>(FavouriteScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    init {
        collectCourses()
    }

    private fun collectCourses() {
        viewModelScope.launch {
            repository.courseList.collect { list ->
                _screenState.value = FavouriteScreenState.Success(data = list)
            }
        }
    }



}