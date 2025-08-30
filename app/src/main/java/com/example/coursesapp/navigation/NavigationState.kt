package com.example.coursesapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

private const val TAG = "NavigationState"

class NavigationState(
    val navHostController: NavHostController
) {
    private val currentRoute: String?
        get() = navHostController.currentBackStackEntry?.destination?.route

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

    fun navigateToBottomNavItem(graphRoute: String) {
        Log.d(TAG, "navigateToBottomNavItem called with graphRoute: $graphRoute")

        // Проверяем, есть ли в стеке экраны, принадлежащие этому графу
        val backStack = navHostController.currentBackStackEntry
        val topScreenInGraph = backStack.v
            .lastOrNull { entry ->
                when (graphRoute) {
                    Graph.HomeGraph.route -> entry.destination.route in listOf(
                        Screen.HomeScreen.route,
                        Screen.CourseDetailScreen.route
                    )
                    Graph.FavouriteGraph.route -> entry.destination.route == Screen.FavouriteScreen.route
                    Graph.AccountGraph.route -> entry.destination.route == Screen.AccountScreen.route
                    else -> false
                }
            }

        if (topScreenInGraph != null) {
            // Если есть экран в стеке, возвращаемся к нему
            Log.d(TAG, "Popping back to existing screen: ${topScreenInGraph.destination.route}")
            navHostController.popBackStack(topScreenInGraph.destination.id, inclusive = false)
        } else {
            // Если стека для графа нет, переходим к начальному экрану графа
            val targetRoute = when (graphRoute) {
                Graph.HomeGraph.route -> Screen.HomeScreen.route
                Graph.FavouriteGraph.route -> Screen.FavouriteScreen.route
                Graph.AccountGraph.route -> Screen.AccountScreen.route
                else -> Screen.HomeScreen.route
            }
            Log.d(TAG, "Navigating to root screen of graph: $targetRoute")
            navigateTo(targetRoute) {
                // Очищаем стек до начального экрана графа
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
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