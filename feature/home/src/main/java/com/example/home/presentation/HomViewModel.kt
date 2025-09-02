package com.example.home.presentation

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

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val _loadingIds = MutableStateFlow<Set<String>>(emptySet())
    val loadingIds = _loadingIds.asStateFlow()

    private var originalCourses: List<Course> = emptyList()
    private var isSortByData = false
    private var isDataLoaded = false

    init {
        loadData()
    }

    fun changeHasLike(course: Course) {
        viewModelScope.launch {
            _loadingIds.value = _loadingIds.value + course.id
            try {
                repository.changeHasLike(course)
            } finally {
                _loadingIds.value = _loadingIds.value - course.id
            }
        }
    }

    fun sortByPublishDate() {
        isSortByData = !isSortByData
        applyFilters()
    }

    private fun loadData() {
        if (isDataLoaded) return

        viewModelScope.launch {
            _screenState.value = HomeScreenState.Loading
            try {
                repository.getCourses()

                collectCourses()
                collectLocalCourses()

                isDataLoaded = true
            } catch (e: Exception) {
                _screenState.value = HomeScreenState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun collectCourses() {
        viewModelScope.launch {
            repository.listCourses.collect { list ->
                if (list.isNotEmpty()) {
                    originalCourses = list
                    applyFilters()
                }
            }
        }
    }

    private fun collectLocalCourses() {
        viewModelScope.launch {
            repository.collectLocalCourses()
        }
    }

    private fun applyFilters() {
        val sorted = if (isSortByData) {
            originalCourses.sortedByDescending { it.publishDate }
        } else {
            originalCourses
        }
        _screenState.value = HomeScreenState.Succsed(list = sorted)
    }
}