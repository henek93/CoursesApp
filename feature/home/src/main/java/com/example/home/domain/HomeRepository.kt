package com.example.home.domain

import com.example.domain.entity.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface HomeRepository {

    val listCourses: SharedFlow<List<Course>>

    suspend fun getCourses()

    suspend fun changeHasLike(course: Course)
}