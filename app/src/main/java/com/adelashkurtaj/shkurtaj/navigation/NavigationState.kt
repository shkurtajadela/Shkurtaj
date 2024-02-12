package com.adelashkurtaj.shkurtaj.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    fun navigateToFilmDetailsScreen(id: Int) {
        navHostController.navigate(Screen.FilmsDetailsScreen.getRouteWithArgs(id = id))
    }

    fun navigateToSearchScreen(area: Int) {
        navHostController.navigate(Screen.SearchFilmsScreen.getRouteWithArgs(area = area))
    }

    fun navigateBack() {
        navHostController.navigateUp()
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}