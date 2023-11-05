package com.dayker.viewed.network.common.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.networkNavGraph(
    navController: NavHostController,
    windowSize: WindowSizeClass
) {
//    navigation(
//        route = GRAPH_NAME,
//        startDestination = NetworkScreen.SearchScreen.route
//    ) {
//
//    }
}

object NetworkNavGraphConstants {
    const val GRAPH_NAME = "network_graph"
}