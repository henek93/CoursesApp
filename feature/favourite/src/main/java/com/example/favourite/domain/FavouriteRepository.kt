package com.example.favourite.domain

import com.example.domain.entity.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface FavouriteRepository {

    val courseList: Flow<List<Course>>


    suspend fun changeHasLike(course: Course)
}