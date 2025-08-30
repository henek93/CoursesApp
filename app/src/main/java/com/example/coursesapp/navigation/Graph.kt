package com.example.coursesapp.navigation

sealed class Graph(val route: String) {
    object AuthGraph : Graph("auth_graph")
    object MainGraph : Graph("main_graph")
    object HomeGraph: Graph("home_graph")
    object FavouriteGraph: Graph("favourite_graph")
    object AccountGraph: Graph("AccountGraph")
}