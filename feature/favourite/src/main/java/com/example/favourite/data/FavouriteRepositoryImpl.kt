package com.example.favourite.data

import com.example.data.local.db.CourseDao
import com.example.data.local.model.toEntity
import com.example.data.local.model.toModel
import com.example.domain.entity.Course
import com.example.favourite.domain.FavouriteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao,
) : FavouriteRepository {

    override val courseList = courseDao.getFlowCourses()


    override suspend fun changeHasLike(course: Course) {
        if (course.hasLike) {
            courseDao.deleteCourseFromFavourite(course.id)
        } else {
            courseDao.addCourseToFavourite(course.copy(hasLike = true).toModel())
        }
    }

}