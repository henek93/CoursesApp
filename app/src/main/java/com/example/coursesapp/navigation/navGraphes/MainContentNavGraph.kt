package com.example.coursesapp.navigation.navGraphes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.coursesapp.navigation.Graph

@Composable
fun MainContentNavGraph(
    currentTab: String,
    homeNavController: NavHostController,
    favouriteNavController: NavHostController,
    accountNavController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit,
    courseDetailScreen: @Composable () -> Unit
) {
    when (currentTab) {
        Graph.HomeGraph.route -> {
            HomeNavGraph(
                navController = homeNavController,
                homeScreenContent = homeScreenContent,
                courseDetailScreen = courseDetailScreen
            )
        }

        Graph.FavouriteGraph.route -> {
            FavouriteNavGraph(
                navController = favouriteNavController,
                favouriteScreenContent = favouriteScreenContent
            )
        }

        Graph.AccountGraph.route -> {
            AccountNavGraph(
                navController = accountNavController,
                accountScreenContent = accountScreenContent
            )
        }
    }
}