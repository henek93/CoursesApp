package com.example.coursesapp.navigation.navGraphes

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    courseDetailScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        route = Graph.HomeGraph.route
    ) {
        composable(
            route = Screen.HomeScreen.route
        ) {
            homeScreenContent()
        }

        composable(
            route = Screen.CourseDetailScreen.route
        ) {
            courseDetailScreen()
        }
    }
}
