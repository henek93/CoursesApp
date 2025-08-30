package com.lingualink.app.navigation

import androidx.core.net.toUri

/**
 * Определение экранов приложения с маршрутами для навигации.
 */
sealed class Screen(val route: String) {
    object HelloScreen : Screen("hello_screen")
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object ResetPasswordScreen : Screen("reset_password_screen")

    object ChatsScreen : Screen("chats_screen")
    object BooksScreen : Screen("books_screen")

    object DictionaryScreen : Screen("dictionary_screen")
    object LearnScreen : Screen("learn_screen")

    object ChatBotScreen : Screen("chat_bot_screen/{chatId}/{chatName}")
    object ReadingScreen : Screen("reading_screen/{bookId}")
    object FlashcardScreen : Screen("flashcard_screen/{wordId}")


    companion object {
        const val KEY_CHAT_ID = "chatId"
        const val KEY_CHAT_NAME = "chatName"
        const val KEY_BOOK_ID = "bookId"
        const val KEY_WORD_ID = "wordId"

        fun chatBotScreen(chatId: String, chatName: String): String {
            val encodedChatId = chatId.toUri().toString()
            val encodedChatName = chatName.toUri().toString()
            return "chat_bot_screen/$encodedChatId/$encodedChatName"
        }

        // Безопасная генерация маршрута для ReadingScreen
        fun readingScreen(bookId: Int): String {
            return "reading_screen/$bookId"
        }

        fun flashcardScreen(wordId: Long): String {
            return "flashcard_screen/$wordId"
        }
    }
}