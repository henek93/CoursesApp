package com.example.home.data

import com.example.data.local.db.CourseDao
import com.example.data.local.model.toEntity
import com.example.data.local.model.toModel
import com.example.data.network.ApiService
import com.example.data.network.dto.toEntity
import com.example.domain.entity.Course
import com.example.home.domain.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val courseDao: CourseDao
) : HomeRepository {

    private val _listCourses = MutableStateFlow<List<Course>>(emptyList())
    override val listCourses: StateFlow<List<Course>> = _listCourses.asStateFlow()

    override suspend fun getCourses() {
        try {
            val network = getCoursesFromNetwork()
            val localIds = getCoursesFromDb().map { it.id }.toSet()

            val merged = network.map { course ->
                course.copy(hasLike = localIds.contains(course.id))
            }

            _listCourses.value = merged
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun collectLocalCourses() {
        courseDao.getFlowCourses().collect { localList ->
            val current = _listCourses.value
            if (current.isNotEmpty()) {
                val localIds = localList.map { it.id }.toSet()
                _listCourses.value = current.map { it.copy(hasLike = localIds.contains(it.id)) }
            }
        }
    }

    override suspend fun changeHasLike(course: Course) {
        if (course.hasLike) {
            courseDao.deleteCourseFromFavourite(course.id)
        } else {
            courseDao.addCourseToFavourite(course.copy(hasLike = true).toModel())
        }
    }

    private suspend fun getCoursesFromDb(): List<Course> {
        return courseDao.getAll().map { it.toEntity() }
    }

    private suspend fun getCoursesFromNetwork(): List<Course> {
        return apiService.getCourses().courses.map { it.toEntity() }
    }
}