package com.example.coursesapp.navigation.navGraphes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen

private const val TAG = "MainNavGraph"

fun NavGraphBuilder.mainNavGraph(
    homeScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit,
    courseDetailScreen: @Composable () -> Unit
) {
    Log.d(TAG, "Building mainNavGraph with startDestination: ${Screen.HomeScreen.route}")
    navigation(
        startDestination = Screen.HomeScreen.route,
        route = Graph.MainGraph.route
    ) {

        composable(
            route = Screen.HomeScreen.route,
        ) {
            homeScreenContent()
        }

        composable(route = Screen.FavouriteScreen.route) {
            favouriteScreenContent()
        }
        composable(route = Screen.AccountScreen.route) {
            accountScreenContent()
        }
        composable(route = Screen.CourseDetailScreen.route) {
            courseDetailScreen()
        }


//        HomeNavGraph(
//            homeScreenContent = homeScreenContent,
//            courseDetailScreen = courseDetailScreen
//        )
//
//        FavouriteNavGraph(
//            favouriteScreenContent = favouriteScreenContent
//        )
//
//        AccountNavGraph(
//            accountScreenContent = accountScreenContent
//        )

    }
}