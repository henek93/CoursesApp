package com.example.coursesapp.navigation

import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController

private const val TAG = "NavigationState"

/**
 * Состояние навигации с множественными стеками для каждой вкладки. Позволяет сохранять состояние
 * каждой вкладки и иметь единый глобальный стек навигации.
 */
class MultiStackNavigationState {
    // Отдельные контроллеры для каждой вкладки
    private val _homeNavController = mutableStateOf<NavHostController?>(null)
    private val _favouriteNavController = mutableStateOf<NavHostController?>(null)
    private val _accountNavController = mutableStateOf<NavHostController?>(null)

    // Глобальный стек для отслеживания порядка посещения вкладок
    private val _globalNavigationStack = mutableStateListOf<String>()

    // Текущая активная вкладка
    private val _currentTab = mutableStateOf(Graph.HomeGraph.route)

    val currentTab: State<String> = _currentTab
    val globalNavigationStack: List<String> = _globalNavigationStack

    /** Регистрирует NavHostController для указанной вкладки */
    fun setNavController(tabRoute: String, navController: NavHostController) {
        when (tabRoute) {
            Graph.HomeGraph.route -> _homeNavController.value = navController
            Graph.FavouriteGraph.route -> _favouriteNavController.value = navController
            Graph.AccountGraph.route -> _accountNavController.value = navController
        }
        Log.d(TAG, "NavController set for tab: $tabRoute")
    }

    /** Возвращает NavHostController для текущей активной вкладки */
    fun getCurrentNavController(): NavHostController? {
        return when (_currentTab.value) {
            Graph.HomeGraph.route -> _homeNavController.value
            Graph.FavouriteGraph.route -> _favouriteNavController.value
            Graph.AccountGraph.route -> _accountNavController.value
            else -> null
        }
    }

    /** Переключает на указанную вкладку, сохраняя глобальный стек навигации */
    fun switchToTab(tabRoute: String) {
        if (_currentTab.value == tabRoute) {
            Log.d(TAG, "Already on tab: $tabRoute")
            return
        }

        Log.d(TAG, "Switching from ${_currentTab.value} to tab: $tabRoute")

        // Добавляем текущую вкладку в глобальный стек если её там нет на вершине
        if (_globalNavigationStack.isEmpty() || _globalNavigationStack.last() != _currentTab.value
        ) {
            _globalNavigationStack.add(_currentTab.value)
            Log.d(TAG, "Added ${_currentTab.value} to global stack")
        }

        // Переключаемся на новую вкладку
        _currentTab.value = tabRoute

        Log.d(TAG, "Global stack: ${_globalNavigationStack.joinToString(" -> ")}")
        Log.d(TAG, "Current tab: ${_currentTab.value}")
    }

    /** Навигация внутри текущей вкладки */
    fun navigateInCurrentTab(route: String) {
        val currentNavController = getCurrentNavController()
        if (currentNavController != null) {
            val currentRoute = currentNavController.currentBackStackEntry?.destination?.route
            if (currentRoute == route) {
                Log.d(TAG, "Already on route: $route in current tab")
                return
            }
            Log.d(TAG, "Navigating in current tab (${_currentTab.value}) to: $route")
            currentNavController.navigate(route) {
                launchSingleTop = true
                restoreState = true
            }
        } else {
            Log.e(TAG, "No NavController found for current tab: ${_currentTab.value}")
        }
    }

    /** Навигация назад с поддержкой глобального стека */
    fun navigateBack(): Boolean {
        val currentNavController = getCurrentNavController()

        // Сначала пытаемся вернуться внутри текущей вкладки
        if (currentNavController?.previousBackStackEntry != null) {
            Log.d(TAG, "Navigating back within current tab: ${_currentTab.value}")
            currentNavController.popBackStack()
            return true
        }

        // Если в текущей вкладке некуда возвращаться, переключаемся на предыдущую вкладку
        if (_globalNavigationStack.isNotEmpty()) {
            val previousTab = _globalNavigationStack.removeLastOrNull()
            if (previousTab != null) {
                Log.d(TAG, "Switching to previous tab from global stack: $previousTab")
                _currentTab.value = previousTab
                Log.d(TAG, "Updated global stack: ${_globalNavigationStack.joinToString(" -> ")}")
                return true
            }
        }

        Log.d(TAG, "No more navigation history - at root")
        return false
    }

    /** Получение текущего маршрута активной вкладки */
    fun getCurrentRoute(): String? {
        return getCurrentNavController()?.currentBackStackEntry?.destination?.route
    }
}

// Оставляем старый класс для совместимости, если где-то еще используется
class NavigationState(val navHostController: NavHostController) {
    private val currentRoute: String?
        get() = navHostController.currentBackStackEntry?.destination?.route

    fun navigateTo(route: String, options: NavOptionsBuilder.() -> Unit = {}) {
        if (currentRoute == route) {
            Log.d(TAG, "Already on route: $route, skipping navigation")
            return
        }
        Log.d(TAG, "Navigating to route: $route")
        navHostController.navigate(route) {
            launchSingleTop = false
            restoreState = true
            options()
        }
    }

    fun navigateBack() {
        if (navHostController.previousBackStackEntry != null) {
            navHostController.popBackStack()
        } else {
            Log.d(TAG, "No previous back stack entry to navigate back from: $currentRoute")
        }
    }
}

/** Создает и запоминает состояние множественной навигации */
@Composable
fun rememberMultiStackNavigationState(): MultiStackNavigationState {
    return remember { MultiStackNavigationState() }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember(navHostController) { NavigationState(navHostController) }
}
