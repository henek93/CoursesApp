package com.example.coursesapp.navigation.navGraphes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.authorization.presentation.AuthorizationScreen
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen

private const val AUTH_NAV_GRAPH_TAG = "AuthNavGraph"

/**
 * Граф навигации для feature:authorization модуля
 * Интегрирует экраны авторизации в общую навигацию приложения
 */
@Composable
fun AuthNavGraph(
    navController: NavHostController,
    onSignInSuccess: () -> Unit
) {
    Log.d(AUTH_NAV_GRAPH_TAG, "Creating AuthNavGraph using feature:authorization module")

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        route = Graph.AuthGraph.route
    ) {
        composable(Screen.LoginScreen.route) {
            Log.d(AUTH_NAV_GRAPH_TAG, "Rendering AuthorizationScreen from feature module")

            AuthorizationScreen(
                signIn = {
                    Log.d(AUTH_NAV_GRAPH_TAG, "Login successful from feature module")
                    onSignInSuccess()
                }
            )
        }
    }
}