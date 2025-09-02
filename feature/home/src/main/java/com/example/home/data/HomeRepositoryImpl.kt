package com.example.home.data

import com.example.data.local.db.CourseDao
import com.example.data.local.model.toEntity
import com.example.data.local.model.toModel
import com.example.data.network.ApiService
import com.example.data.network.dto.toEntity
import com.example.domain.entity.Course
import com.example.home.domain.HomeRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val courseDao: CourseDao
) : HomeRepository {

    private val _listCourses = MutableSharedFlow<List<Course>>()
    override val listCourses = _listCourses.asSharedFlow()

    override suspend fun getCourses() {
        val local = getCoursesFromDb()
        val network = getCoursesFromNetwork()

        val localIds = local.map { it.id }.toSet()

        val merged = network.map { course ->
            course.copy(hasLike = localIds.contains(course.id))
        }

        _listCourses.emit(merged)
    }


    private suspend fun getCoursesFromDb(): List<Course> {
        val localCourses = courseDao.getAll().map { it.toEntity() }
        return localCourses
    }

    private suspend fun getCoursesFromNetwork(): List<Course> {
        val courses = apiService.getCourses().courses.map { it.toEntity() }
        return courses
    }


    override suspend fun changeHasLike(course: Course) {
        if (course.hasLike) {
            courseDao.deleteCourseFromFavourite(course.id)
        } else {
            courseDao.addCourseToFavourite(course.toModel())
        }

        getCourses()
    }


}