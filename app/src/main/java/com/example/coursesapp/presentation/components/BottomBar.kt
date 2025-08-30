package com.example.coursesapp.presentation.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.coursesapp.navigation.Graph
import com.example.coursesapp.navigation.MultiStackNavigationState

/** Bottom navigation bar для архитектуры с множественными стеками */
@Composable
fun MultiStackBottomNavigationBar(
    multiStackNavState: MultiStackNavigationState,
    currentTab: String
) {
    val MULTI_STACK_BOTTOM_BAR_TAG = "MultiStackBottomBar"
    Log.d(
        MULTI_STACK_BOTTOM_BAR_TAG,
        "MultiStackBottomNavigationBar composed with currentTab: $currentTab"
    )

    val items =
        listOf(
            BottomNavItem(Graph.HomeGraph.route, "Главная", Icons.Default.Home),
            BottomNavItem(Graph.FavouriteGraph.route, "Избранное", Icons.Default.Favorite),
            BottomNavItem(Graph.AccountGraph.route, "Аккаунт", Icons.Default.AccountCircle)
        )

    NavigationBar(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                ),
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        tint =
                            if (currentTab == item.route)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                label = {
                    Text(
                        item.label,
                        color =
                            if (currentTab == item.route)
                                MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                },
                selected = currentTab == item.route,
                onClick = {
                    Log.d(
                        MULTI_STACK_BOTTOM_BAR_TAG,
                        "Clicked on ${item.label} - target tab: ${item.route}"
                    )
                    multiStackNavState.switchToTab(item.route)
                },
                alwaysShowLabel = false
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)