package com.example.coursesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.coursesapp.navigation.navGraphes.mainNavGraph

@Composable
fun AppNavGraph(
    navController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit,
    courseDetailScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Graph.MainGraph.route
    ) {
        mainNavGraph(
            homeScreenContent = homeScreenContent,
            favouriteScreenContent = favouriteScreenContent,
            accountScreenContent = accountScreenContent,
            courseDetailScreen = courseDetailScreen
        )
    }
}