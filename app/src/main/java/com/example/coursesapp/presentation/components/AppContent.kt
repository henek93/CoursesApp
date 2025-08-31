package com.example.coursesapp.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.coursesapp.auth.AuthState
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.RootNavGraph
import com.example.coursesapp.presentation.MainViewModel

/**
 * Корневой компонент многомодульного приложения с двухуровневой навигацией
 * Координирует работу feature модулей через состояние авторизации
 */
private const val APP_CONTENT_TAG = "AppContent"

@Composable
fun AppContent(
    authState: AuthState
) {
    Log.d(APP_CONTENT_TAG, "AppContent composed - managing multi-module navigation")

    val rootNavController = rememberNavController()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                Log.d(APP_CONTENT_TAG, "User authenticated - navigating to MainGraph")
                rootNavController.navigate(Graph.MainGraph.route) {
                    popUpTo(0)
                }
            }

            is AuthState.Unauthenticated -> {
                Log.d(APP_CONTENT_TAG, "User unauthenticated - navigating to AuthGraph")
                rootNavController.navigate(Graph.AuthGraph.route) {
                    popUpTo(0)
                }
            }

            AuthState.Loading -> {

            }
        }
    }

    RootNavGraph(
        navController = rootNavController,
        authState = authState
    )
}