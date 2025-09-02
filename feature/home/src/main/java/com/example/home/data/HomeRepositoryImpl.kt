package com.example.home.data

import com.example.data.network.ApiService
import com.example.data.network.dto.toEntity
import com.example.domain.entity.Course
import com.example.home.domain.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRepository {

    private val _listCourses = MutableSharedFlow<List<Course>>()
    override val listCourses = _listCourses.asSharedFlow()

    override suspend fun getCourses() {
        getCoursesFromNetwork()
    }


    private suspend fun getCoursesFromNetwork() {
        val courses = apiService.getCourses().courses.map { it.toEntity() }
        _listCourses.emit(courses)
    }


    override suspend fun changeHasLike(course: Course) {
        TODO("Not yet implemented")
    }


}