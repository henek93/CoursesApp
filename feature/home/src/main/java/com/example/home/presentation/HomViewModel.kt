package com.example.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Course
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

    private val TAG = "HomViewModel"

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    init {
        Log.d(TAG, "init: ViewModel created")
        collectCourses()
        getCourses()
    }

    private fun collectCourses() {
        viewModelScope.launch {
            Log.d(TAG, "collectCourses: start collecting from repository.listCourses")
            repository.listCourses.collect { list ->
                Log.d(TAG, "collectCourses: received ${list.size} courses")
                _screenState.value = HomeScreenState.Succsed(list = list)
            }
        }
    }

    private fun getCourses() {
        viewModelScope.launch {
            Log.d(TAG, "getCourses: start")
            _screenState.value = HomeScreenState.Loading
            try {
                repository.getCourses()
                Log.d(TAG, "getCourses: repository.getCourses() finished successfully")
            } catch (e: Exception) {
                Log.e(TAG, "getCourses: failed with exception", e)
                _screenState.value = HomeScreenState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun changeHasLike(course: Course) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "changeHasLike: course=${course.id}, current like=${course.hasLike}")
                repository.changeHasLike(course)
                Log.d(TAG, "changeHasLike: success for course=${course.id}")
            } catch (e: Exception) {
                Log.e(TAG, "changeHasLike: failed for course=${course.id}", e)
            }
        }
    }
}