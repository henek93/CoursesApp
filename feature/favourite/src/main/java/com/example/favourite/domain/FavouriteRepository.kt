package com.example.favourite.domain

import com.example.domain.entity.Course
import kotlinx.coroutines.flow.MutableSharedFlow

interface FavouriteRepository {

    val courseList: MutableSharedFlow<List<Course>>

    suspend fun getFavouriteCourses()

    suspend fun changeHasLike(course: Course)
}