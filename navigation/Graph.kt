package com.lingualink.app.navigation

/**
 * Определение графов навигации для группировки экранов.
 */
sealed class Graph(val route: String) {
    object AuthGraph : Graph("auth_graph")
    object MainGraph : Graph("main_graph")
    object ChatsGraph : Graph("chats_graph")
    object BookGraph : Graph("book_graph")
    object DictionaryGraph : Graph("dictionary_graph")

}