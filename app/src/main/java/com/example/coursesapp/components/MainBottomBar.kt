package com.example.coursesapp.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.NavigationState
import com.example.coursesapp.navigation.Screen

const val TAG = "BottomNavigationBar"

@Composable
fun BottomNavigationBar(
    navigationState: NavigationState,
    currentRoute: String
) {
    Log.d(TAG, "BottomNavigationBar composed with currentRoute: $currentRoute")
    var selectedItem by remember { mutableIntStateOf(1) }


    val items = getListBottomNatItem()

    selectedItem = getSelectedItem(currentRoute)


    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ),
        containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        tint = if (selectedItem == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                label = {
                    Text(
                        item.label,
                        color = if (selectedItem == index)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    if (selectedItem != index) {
                        Log.d(TAG, "Navigating to ${item.label} - ${item.route}")
                        navigationState.navigateToBottomNavItem(item.route)
                    } else {
                        Log.d(TAG, "Item ${item.label} already selected")
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}


fun getSelectedItem(currentRoute: String) = when (currentRoute) {
    Screen.HomeScreen.route, Screen.CourseDetailScreen.route -> 0
    Screen.FavouriteScreen.route -> 1
    Screen.AccountScreen.route -> 2
    else -> {
        Log.w(TAG, "Unknown currentRoute: $currentRoute, defaulting to Chats (index 1)")
        1
    }
}

fun getListBottomNatItem() = listOf(
    BottomNavItem(Graph.HomeGraph.route, "Главная", Icons.Default.Home),
    BottomNavItem(Graph.FavouriteGraph.route, "Избранное", Icons.Default.Favorite),
    BottomNavItem(Graph.AccountGraph.route, "Аккаунт", Icons.Default.AccountCircle)
)

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)