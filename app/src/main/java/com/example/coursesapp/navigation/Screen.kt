package com.example.coursesapp.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")

    object HomeScreen : Screen("home_screen")
    object FavouriteScreen : Screen("favourite_screen")
    object AccountScreen : Screen("account_screen")


    object CourseDetailScreen : Screen("course_detail_screen")

    companion object {
        const val KEY_COURSE_ID = "courseId"

        /**
         * Создает маршрут для экрана деталей курса с переданным courseId Использует URL-кодирование
         * для безопасной передачи параметров
         */
        fun courseDetailScreen(courseId: String): String {
            val encodedCourseId = URLEncoder.encode(courseId, StandardCharsets.UTF_8.toString())
            return "course_detail_screen/$encodedCourseId"
        }
    }
}
