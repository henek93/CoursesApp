package com.lingualink.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.authScreenNavGraph(
    navController: NavHostController,
    helloScreenContent: @Composable () -> Unit,
    loginScreenContent: @Composable () -> Unit,
    signUpScreenContent: @Composable () -> Unit,
    resetPasswordScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.HelloScreen.route,
        route = Graph.AuthGraph.route
    ) {
        composable(
            route = Screen.HelloScreen.route,
        ) {
            helloScreenContent()
        }
        composable(
            route = Screen.LoginScreen.route,
        ) {
            loginScreenContent()
        }
        composable(
            route = Screen.SignUpScreen.route,
        ) {
            signUpScreenContent()
        }
        composable(
            route = Screen.ResetPasswordScreen.route,
        ) {
            resetPasswordScreenContent()
        }
    }
}