package com.dayker.viewed.common.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dayker.viewed.common.presentation.navigation.components.AppBottomBar
import com.dayker.viewed.common.presentation.navigation.components.AppNavigationRail
import com.dayker.viewed.common.presentation.navigation.graphs.NavigationBarNavGraph
import com.dayker.viewed.common.presentation.navigation.screens.DestinationScreen

@Composable
fun NavigationScreen(
    navController: NavHostController = rememberNavController(),
    windowSize: WindowSizeClass
) {
    val screens = listOf(
        DestinationScreen.Watched,
        DestinationScreen.Discover,
        DestinationScreen.Details,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AppNavigationPortrait(
                navController,
                windowSize,
                currentDestination,
                screens,
                elementOnClick = { route ->
                    elementOnClick(route = route, navController = navController)
                }
            )
        }

        else -> {
            AppNavigationLandscape(
                navController,
                windowSize,
                currentDestination,
                screens,
                elementOnClick = { route ->
                    elementOnClick(route = route, navController = navController)
                }
            )
        }

    }
}

fun elementOnClick(route: String, navController: NavHostController) {
    val prePreviousEntry = navController.previousBackStackEntry
    navController.navigate(route = route) {
        prePreviousEntry?.destination?.let {
            popUpTo(it.id) {
                inclusive = true
            }
        }
    }
}

@Composable
fun AppNavigationLandscape(
    navController: NavHostController,
    windowSize: WindowSizeClass,
    currentDestination: NavDestination?,
    screens: List<DestinationScreen>,
    elementOnClick: (String) -> Unit
) {
    Row {
        AppNavigationRail(
            currentDestination = currentDestination,
            screens = screens,
            elementOnClick = elementOnClick
        )
        NavigationBarNavGraph(navController = navController, windowSize = windowSize)
    }
}

@Composable
fun AppNavigationPortrait(
    navController: NavHostController,
    windowSize: WindowSizeClass,
    currentDestination: NavDestination?,
    screens: List<DestinationScreen>,
    elementOnClick: (String) -> Unit
) {
    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentDestination = currentDestination,
                screens = screens,
                elementOnClick = elementOnClick
            )
        }
    ) {
        NavigationBarNavGraph(
            navController = navController,
            windowSize = windowSize,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        )
    }
}

