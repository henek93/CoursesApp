package com.example.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entity.Course
import com.example.utils.formatToRussian
import com.example.utils.toDate

@Entity(tableName = "courses")
data class CourseModel(
    @PrimaryKey val id: String,
    val title: String,
    val text: String,
    val price: String,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)

fun Course.toModel() = CourseModel(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate.formatToRussian(),
    hasLike = hasLike,
    publishDate = publishDate.formatToRussian()
)

fun CourseModel.toEntity() = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate.toDate(),
    hasLike = hasLike,
    publishDate = publishDate.toDate()
)