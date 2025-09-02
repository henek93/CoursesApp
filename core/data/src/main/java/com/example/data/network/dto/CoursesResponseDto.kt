package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class CoursesResponseDto(
    @SerializedName("courses") val courses: List<CourseDto>
)