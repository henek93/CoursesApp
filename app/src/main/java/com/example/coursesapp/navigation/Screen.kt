package com.example.coursesapp.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")

    object HomeScreen : Screen("home_screen")
    object FavouriteScreen : Screen("favourite_screen")
    object AccountScreen : Screen("account_screen")

    object CourseDetailScreen : Screen("course_detail_screen")

    companion object {
        const val KEY_COURSE_ID = "courseId"

        fun courseDetailScreen(courseId: String): String {
            return "course_detail_screen/$courseId"
        }
    }
}