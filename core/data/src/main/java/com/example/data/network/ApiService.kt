package com.example.data.network

import com.example.data.network.dto.CoursesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("uc")
    suspend fun getCourses(
        @Query("id") id: String = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q",
        @Query("export") export: String = "download"
    ): CoursesResponseDto
}