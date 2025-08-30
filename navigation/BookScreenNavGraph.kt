package com.lingualink.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

fun NavGraphBuilder.bookScreenNavGraph(
    navController: NavHostController,
    booksScreenContent: @Composable (navigateToReading: (Int) -> Unit) -> Unit, // Изменяем String на Int
    readingScreenContent: @Composable (bookId: Int, navigateBack: () -> Unit) -> Unit, // Изменяем fileUri на bookId
    navigateToReading: (Int) -> Unit, // Изменяем String на Int
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = Screen.BooksScreen.route,
        route = Graph.BookGraph.route
    ) {
        composable(
            route = Screen.BooksScreen.route,
        ) {
            booksScreenContent { bookId ->
                navigateToReading(bookId)
            }
        }
        composable(
            route = Screen.ReadingScreen.route,
            arguments = listOf(
                navArgument(Screen.KEY_BOOK_ID) {
                    type = NavType.IntType // Изменяем StringType на IntType
                }
            ),
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt(Screen.KEY_BOOK_ID)
                ?: throw RuntimeException("Book ID is null")
            readingScreenContent(bookId) {
                navigateBack()
            }
        }
    }
}