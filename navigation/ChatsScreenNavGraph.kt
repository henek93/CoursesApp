package com.lingualink.app.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

private const val TAG = "ChatScreenNavGraph"

fun NavGraphBuilder.chatScreenNavGraph(
    navController: NavHostController,
    chatsScreenContent: @Composable (navigateToChatBot: (String, String) -> Unit) -> Unit,
    chatBotScreenContent: @Composable (chatId: String, navigateBack: () -> Unit, chatName: String) -> Unit,
    navigateToChatBot: (String, String) -> Unit,
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = Screen.ChatsScreen.route,
        route = Graph.ChatsGraph.route
    ) {
        composable(
            route = Screen.ChatsScreen.route,
        ) {
            Log.d(TAG, "Rendering ChatsScreen")
            chatsScreenContent { chatId, chatName ->
                navigateToChatBot(chatId, chatName)
            }
        }
        composable(
            route = Screen.ChatBotScreen.route,
            arguments = listOf(
                navArgument(Screen.KEY_CHAT_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(Screen.KEY_CHAT_NAME) {
                    type = NavType.StringType
                    defaultValue = "Unnamed Chat"
                }
            )
        ) { navBackStackEntry ->
            val chatId = navBackStackEntry.arguments?.getString(Screen.KEY_CHAT_ID) ?: ""
            val chatName = navBackStackEntry.arguments?.getString(Screen.KEY_CHAT_NAME) ?: "Unnamed Chat"
            Log.d(TAG, "Rendering ChatBotScreen with chatId: $chatId, chatName: $chatName")
            chatBotScreenContent(chatId, navigateBack, chatName)
        }
    }
}