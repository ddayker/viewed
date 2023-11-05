package com.dayker.viewed.app.navigation.graphs

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dayker.viewed.app.navigation.ui.NavigationScreen

@Composable
fun RootNavigationGraph(navController: NavHostController, windowSize: WindowSizeClass) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.NAVIGATION_BAR
    ) {
        composable(route = Graph.NAVIGATION_BAR) {
            NavigationScreen(windowSize = windowSize)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val NAVIGATION_BAR = "navigation_bar_graph"
}