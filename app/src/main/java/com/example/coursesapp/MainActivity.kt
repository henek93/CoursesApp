package com.example.coursesapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.account.presentation.AccountScreen
import com.example.coursesapp.components.BottomNavigationBar
import com.example.coursesapp.navigation.AppNavGraph
import com.example.coursesapp.navigation.Screen
import com.example.coursesapp.navigation.rememberNavigationState
import com.example.coursesapp.ui.theme.CoursesAppTheme
import com.example.favourite.presentation.FavouriteScreen
import com.example.home.presentation.HomeScreen

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                MainScreen()
            }
        }
    }
}

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


@Composable
fun CourseDetailScreen(courseId: String, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Детали курса: $courseId")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBackClick) {
            Text("Назад")
        }
    }
}


