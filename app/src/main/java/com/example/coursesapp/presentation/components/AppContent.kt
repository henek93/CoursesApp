package com.example.coursesapp.presentation.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.auth.state.AuthState
import com.example.auth.viewModel.AuthViewModel
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.RootNavGraph

/**
 * Корневой компонент многомодульного приложения с двухуровневой навигацией
 * Координирует работу feature модулей через состояние авторизации
 */
private const val APP_CONTENT_TAG = "AppContent"

@Composable
fun AppContent() {
    Log.d(APP_CONTENT_TAG, "AppContent composed - managing multi-module navigation")

    val authViewModel = viewModel<AuthViewModel>()
    val authState by authViewModel.authState
    val rootNavController = rememberNavController()

    // Автоматическое переключение между графами при изменении состояния авторизации
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                Log.d(APP_CONTENT_TAG, "User authenticated - navigating to MainGraph")
                rootNavController.navigate(Graph.MainGraph.route) {
                    popUpTo(0) // Очищаем весь стек
                }
            }

            is AuthState.Unauthenticated -> {
                Log.d(APP_CONTENT_TAG, "User unauthenticated - navigating to AuthGraph")
                rootNavController.navigate(Graph.AuthGraph.route) {
                    popUpTo(0) // Очищаем весь стек
                }
            }

            AuthState.Loading -> {
                Log.d(APP_CONTENT_TAG, "Auth state loading...")
            }
        }
    }

    RootNavGraph(
        navController = rootNavController,
        authViewModel = authViewModel,
        authState = authState
    )
}