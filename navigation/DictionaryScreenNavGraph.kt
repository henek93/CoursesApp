package com.lingualink.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavGraphBuilder.dictionaryScreenNavGraph(
    navController: NavHostController, // Добавляем параметр navController
    dictionaryScreenContent: @Composable () -> Unit,
    learnScreenContent: @Composable () -> Unit,
    flashcardScreenContent: @Composable (wordId: Long) -> Unit
) {
    navigation(
        startDestination = Screen.DictionaryScreen.route,
        route = Graph.DictionaryGraph.route
    ) {
        composable(
            route = Screen.FlashcardScreen.route,
            arguments = listOf(
                navArgument(Screen.KEY_WORD_ID) {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ) { navBackStackEntry ->
            val wordId = navBackStackEntry.arguments?.getLong(Screen.KEY_WORD_ID) ?: 0L
            flashcardScreenContent(wordId)
        }
        composable(route = Screen.LearnScreen.route) {
            learnScreenContent()
        }
        composable(
            route = Screen.DictionaryScreen.route,
        ) {
            dictionaryScreenContent()
        }
    }
}