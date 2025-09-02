package com.example.data.network.dto

import com.example.domain.entity.Course
import com.example.utils.toDate
import com.google.gson.annotations.SerializedName

data class CourseDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("text") val text: String,
    @SerializedName("price") val price: String,
    @SerializedName("rate") val rate: Float,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("hasLike") val hasLike: Boolean,
    @SerializedName("publishDate") val publishDate: String
)

fun CourseDto.toEntity() = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate.toDate(),
    hasLike = hasLike,
    publishDate = publishDate.toDate()
)