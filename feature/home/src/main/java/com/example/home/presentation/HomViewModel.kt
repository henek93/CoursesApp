package com.example.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.listCourses.collect { list ->
                _screenState.value = HomeScreenState.Succsed(list = list)
            }
        }
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch {
            repository.getCourses()

        }
    }
}