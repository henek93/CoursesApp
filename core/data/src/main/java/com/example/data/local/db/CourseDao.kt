package com.example.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.model.CourseModel
import com.example.domain.entity.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {


    @Query("SELECT * FROM courses")
    fun getFlowCourses(): Flow<List<Course>>

    @Query("SELECT * FROM courses")
    suspend fun getAll(): List<CourseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourseToFavourite(course: CourseModel)


    @Query("DELETE FROM courses WHERE id = :courseId")
    suspend fun deleteCourseFromFavourite(courseId: String)

    @Query("DELETE FROM courses")
    suspend fun clearAllCourses()
}