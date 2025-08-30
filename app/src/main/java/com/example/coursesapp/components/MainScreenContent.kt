package com.example.coursesapp.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.account.presentation.AccountScreen
import com.example.coursesapp.CourseDetailScreen
import com.example.coursesapp.navigation.AppNavGraph
import com.example.coursesapp.navigation.Screen
import com.example.coursesapp.navigation.rememberNavigationState
import com.example.favourite.presentation.FavouriteScreen
import com.example.home.presentation.HomeScreen

const val Tag = "MainScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Log.d(TAG, "MainScreen composed")
    val navigationState = rememberNavigationState()
    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.HomeScreen.route
    Log.d(TAG, "Current route: $currentRoute")


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Log.d(TAG, "Showing BottomBar for route: $currentRoute")
            BottomNavigationBar(
                navigationState = navigationState,
                currentRoute = currentRoute
            )

        }
    ) { paddingValues ->
        AppNavGraph(
            navController = navigationState.navHostController,
            homeScreenContent = {
                HomeScreen(paddingValues, navigateDetailCoures = {
                    navigationState.navigateTo(Screen.CourseDetailScreen.route)
                })
            },
            favouriteScreenContent = {
                FavouriteScreen()
            },
            accountScreenContent = {
                AccountScreen()
            },
            courseDetailScreen = {
                CourseDetailScreen("1", onBackClick = { navigationState.navigateBack() })
            },
        )
    }
}

