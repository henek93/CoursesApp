package com.example.home.domain

import com.example.domain.entity.Course
import kotlinx.coroutines.flow.StateFlow

interface HomeRepository {

    val listCourses: StateFlow<List<Course>>

    suspend fun getCourses()

    suspend fun changeHasLike(course: Course)

    suspend fun collectLocalCourses()
}