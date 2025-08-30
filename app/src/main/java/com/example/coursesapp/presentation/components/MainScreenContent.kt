package com.example.coursesapp.presentation.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.account.presentation.AccountScreen
import com.example.coursesapp.presentation.CourseDetailScreen
import com.example.coursesapp.navigation.navGraphes.MainContentNavGraph
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.Screen
import com.example.coursesapp.navigation.rememberMultiStackNavigationState
import com.example.favourite.presentation.FavouriteScreen
import com.example.home.presentation.HomeScreen

/**
 * Главный экран приложения с архитектурой множественных стеков навигации. Каждая вкладка имеет свой
 * независимый стек, но поддерживается единый глобальный стек для кнопки "Назад".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContentAndNavGraph(
    signOut: () -> Unit
) {
    val TAG = "MainScreen"
    Log.d(TAG, "MainScreen composed")

    val multiStackNavState = rememberMultiStackNavigationState()
    val currentTab by multiStackNavState.currentTab

    // Отдельные NavHostController для каждой вкладки
    val homeNavController = rememberNavController()
    val favouriteNavController = rememberNavController()
    val accountNavController = rememberNavController()

    // Регистрируем контроллеры в состоянии навигации
    LaunchedEffect(Unit) {
        multiStackNavState.setNavController(Graph.HomeGraph.route, homeNavController)
        multiStackNavState.setNavController(Graph.FavouriteGraph.route, favouriteNavController)
        multiStackNavState.setNavController(Graph.AccountGraph.route, accountNavController)
        Log.d(TAG, "All NavControllers registered")
    }

    BackHandler {
        val navigationHandled = multiStackNavState.navigateBack()
        if (!navigationHandled) {
            Log.d(TAG, "Navigation back not handled - should exit app")

        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Log.d(TAG, "Showing MultiStackBottomBar for currentTab: $currentTab")
            MultiStackBottomNavigationBar(
                multiStackNavState = multiStackNavState,
                currentTab = currentTab
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MainContentNavGraph(
                currentTab = currentTab,
                homeNavController = homeNavController,
                favouriteNavController = favouriteNavController,
                accountNavController = accountNavController,
                homeScreenContent = {
                    HomeScreen(paddingValues, navigateDetailCoures = {
                        multiStackNavState.navigateInCurrentTab(Screen.CourseDetailScreen.route)
                    })
                },
                favouriteScreenContent = {
                    FavouriteScreen()
                },
                accountScreenContent = {
                    AccountScreen(
                        signOut = signOut
                    )
                },
                courseDetailScreen = {
                    CourseDetailScreen("231e") {
                        multiStackNavState.navigateBack()
                    }
                }
            )
        }
    }
}
