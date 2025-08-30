package com.example.coursesapp.navigation.navGraphes

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen

@Composable
fun AccountNavGraph(
    navController: NavHostController,
    accountScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AccountScreen.route,
        route = Graph.AccountGraph.route
    ) {
        composable(
            route = Screen.AccountScreen.route
        ) {
            accountScreenContent()
        }
    }
}