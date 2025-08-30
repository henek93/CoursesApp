package com.example.coursesapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.auth.state.AuthState
import com.example.auth.viewModel.AuthViewModel
import com.example.authorization.presentation.AuthorizationScreen
import com.example.coursesapp.presentation.components.MainScreenContentAndNavGraph

private const val ROOT_NAV_GRAPH_TAG = "RootNavGraph"

/**
 * Корневой граф навигации для многомодульного приложения
 * Управляет переключением между feature:authorization и основным приложением
 */
@Composable
fun RootNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    authState: AuthState
) {
    Log.d(ROOT_NAV_GRAPH_TAG, "Creating RootNavGraph - authState: ${authState::class.simpleName}")

    val startDestination = when (authState) {
        is AuthState.Authenticated -> Graph.MainGraph.route
        is AuthState.Unauthenticated -> Graph.AuthGraph.route
        AuthState.Loading -> Graph.AuthGraph.route
    }

    Log.d(ROOT_NAV_GRAPH_TAG, "Start destination: $startDestination")

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Graph.AuthGraph.route) {
            Log.d(ROOT_NAV_GRAPH_TAG, "Rendering AuthGraph from feature:authorization")
            AuthorizationScreen(
                signIn = {
                    authViewModel.signIn()
                }
            )
        }

        composable(Graph.MainGraph.route) {
            Log.d(ROOT_NAV_GRAPH_TAG, "Rendering MainGraph with multi-stack architecture")
            MainScreenContentAndNavGraph(
                signOut = {
                    Log.d(ROOT_NAV_GRAPH_TAG, "SignOut from feature:account")
                    authViewModel.signOut()
                }
            )
        }
    }
}