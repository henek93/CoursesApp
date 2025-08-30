package com.lingualink.app.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

private const val TAG = "MainScreenNavGraph"

fun NavGraphBuilder.mainScreenNavGraph(
    navController: NavHostController,
    chatsScreenContent: @Composable (navigateToChatBot: (String, String) -> Unit, navigateToHelloScreen: () -> Unit) -> Unit,
    booksScreenContent: @Composable (navigateToReading: (Int) -> Unit) -> Unit, // Изменяем String на Int
    dictionaryScreenContent: @Composable () -> Unit,
    navigateToHelloScreen: () -> Unit,
    navigateToChatBot: (String, String) -> Unit,
    navigateToReading: (Int) -> Unit // Изменяем String на Int
) {
    Log.d(TAG, "Building mainScreenNavGraph with startDestination: ${Screen.ChatsScreen.route}")
    navigation(
        startDestination = Screen.ChatsScreen.route,
        route = Graph.MainGraph.route
    ) {
        composable(
            route = Screen.BooksScreen.route,
        ) {
            Log.d(TAG, "Rendering BooksScreen")
            booksScreenContent(navigateToReading)
        }
        composable(
            route = Screen.ChatsScreen.route,
        ) {
            Log.d(TAG, "Rendering ChatsScreen")
            chatsScreenContent(navigateToChatBot, navigateToHelloScreen)
        }
        composable(
            route = Screen.DictionaryScreen.route,
        ) {
            Log.d(TAG, "Rendering DictionaryScreen")
            dictionaryScreenContent()
        }
    }
}