package com.lingualink.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    helloScreenContent: @Composable () -> Unit,
    resetPasswordScreenContent: @Composable () -> Unit,
    loginScreenContent: @Composable () -> Unit,
    signUpScreenContent: @Composable () -> Unit,
    chatsScreenContent: @Composable (navigateToChatBot: (String, String) -> Unit, navigateToHelloScreen: () -> Unit) -> Unit,
    booksScreenContent: @Composable (navigateToReading: (Int) -> Unit) -> Unit, // Изменяем String на Int
    dictionaryScreenContent: @Composable () -> Unit,
    chatBotScreenContent: @Composable (chatId: String, navigateBack: () -> Unit, chatName: String) -> Unit,
    readingScreenContent: @Composable (bookId: Int, navigateBack: () -> Unit) -> Unit, // Изменяем fileUri на bookId
    learnScreenContent: @Composable () -> Unit,
    flashcardScreenContent: @Composable (wordId: Long) -> Unit,
    navigateToChatBot: (String, String) -> Unit,
    navigateToReading: (Int) -> Unit, // Изменяем String на Int
    navigateToHelloScreen: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authScreenNavGraph(
            helloScreenContent = helloScreenContent,
            loginScreenContent = loginScreenContent,
            signUpScreenContent = signUpScreenContent,
            resetPasswordScreenContent = resetPasswordScreenContent,
            navController = navController
        )
        mainScreenNavGraph(
            navController = navController,
            chatsScreenContent = chatsScreenContent,
            booksScreenContent = booksScreenContent,
            dictionaryScreenContent = dictionaryScreenContent,
            navigateToHelloScreen = navigateToHelloScreen,
            navigateToChatBot = navigateToChatBot,
            navigateToReading = navigateToReading
        )
        chatScreenNavGraph(
            navController = navController,
            chatsScreenContent = { navigateToChatBot ->
                chatsScreenContent(navigateToChatBot) {
                    navController.navigate(Graph.AuthGraph.route) {
                        popUpTo(Graph.MainGraph.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            },
            chatBotScreenContent = chatBotScreenContent,
            navigateBack = { navController.popBackStack() },
            navigateToChatBot = navigateToChatBot
        )
        bookScreenNavGraph(
            navController = navController,
            booksScreenContent = booksScreenContent,
            readingScreenContent = readingScreenContent,
            navigateBack = { navController.popBackStack() },
            navigateToReading = navigateToReading
        )
        dictionaryScreenNavGraph(
            navController = navController,
            dictionaryScreenContent = dictionaryScreenContent,
            learnScreenContent = learnScreenContent,
            flashcardScreenContent = flashcardScreenContent
        )
    }
}