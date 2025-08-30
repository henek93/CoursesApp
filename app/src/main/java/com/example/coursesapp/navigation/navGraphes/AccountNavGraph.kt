package com.example.coursesapp.navigation.navGraphes

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen

fun NavGraphBuilder.AccountNavGraph(
    accountScreenContent: @Composable () -> Unit,

    ) {
    navigation(
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