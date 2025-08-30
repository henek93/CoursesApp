package com.lingualink.app.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

private const val TAG = "NavigationState"

class NavigationState(
    val navHostController: NavHostController
) {
    private val currentRoute: String?
        get() = navHostController.currentBackStackEntry?.destination?.route

    // Базовый метод навигации с поддержкой опций
    fun navigateTo(route: String, options: NavOptionsBuilder.() -> Unit = {}) {
        if (currentRoute == route) {
            Log.d(TAG, "Already on route: $route, skipping navigation")
            return
        }
        Log.d(TAG, "Navigating to route: $route")
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true
            options()
        }
    }

    fun navigateToChatBot(chatId: String, chatName: String) {
        val route = Screen.chatBotScreen(chatId, chatName)
        if (currentRoute == route) {
            return
        }
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToReading(bookId: Int) {
        val route = Screen.readingScreen(bookId)
        if (currentRoute == route) {
            return
        }
        Log.d(TAG, "Navigating to ReadingScreen with route: $route")
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToFlashcard(wordId: Long) {
        val route = Screen.flashcardScreen(wordId)
        if (currentRoute == route) {
            return
        }
        navHostController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateBack() {
        if (navHostController.previousBackStackEntry != null) {
            navHostController.popBackStack()
        } else {
            Log.d(TAG, "No previous back stack entry to navigate back from: $currentRoute")
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember(navHostController) {
        NavigationState(navHostController)
    }
}