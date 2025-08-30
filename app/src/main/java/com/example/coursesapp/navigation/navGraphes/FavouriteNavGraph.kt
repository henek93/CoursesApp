package com.example.coursesapp.navigation.navGraphes

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen

@Composable
fun FavouriteNavGraph(
    navController: NavHostController,
    favouriteScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FavouriteScreen.route,
        route = Graph.FavouriteGraph.route
    ) {

        composable(route = Screen.FavouriteScreen.route) {
            favouriteScreenContent()
        }
    }
}