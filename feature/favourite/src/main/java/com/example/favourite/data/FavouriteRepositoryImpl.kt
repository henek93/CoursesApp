package com.example.favourite.data

import com.example.data.local.db.CourseDao
import com.example.data.local.model.toEntity
import com.example.domain.entity.Course
import com.example.favourite.domain.FavouriteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao,
) : FavouriteRepository {

    override val courseList = courseDao.getFlowCourses()



    override suspend fun changeHasLike(course: Course) {
        TODO("Not yet implemented")
    }

}