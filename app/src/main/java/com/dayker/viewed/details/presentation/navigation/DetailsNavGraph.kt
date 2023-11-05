package com.dayker.viewed.details.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dayker.viewed.core.presentation.navigation.graphs.Graph
import com.dayker.viewed.core.presentation.navigation.navanimations.scaleInAnimation
import com.dayker.viewed.core.presentation.navigation.navanimations.scaleOutAnimation
import com.dayker.viewed.details.presentation.aboutapp.AboutAppScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController, windowSize: WindowSizeClass) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.AboutApp.route
    ) {
        composable(
            route = DetailsScreen.AboutApp.route,
            enterTransition = {
                scaleInAnimation()
            },
            popEnterTransition = {
                scaleInAnimation()
            },
            exitTransition = {
                scaleOutAnimation()
            },
            popExitTransition = {
                scaleOutAnimation()
            }
        ) {
            AboutAppScreen(
                windowSize = windowSize,
                onBackButtonClicked = { navController.navigateUp() }
            )
        }
    }
}

