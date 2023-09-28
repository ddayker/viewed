package com.dayker.viewed.presentation.navigation.graphs

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dayker.viewed.presentation.details.aboutapp.AboutAppScreen
import com.dayker.viewed.presentation.navigation.navanimations.scaleInAnimation
import com.dayker.viewed.presentation.navigation.navanimations.scaleOutAnimation
import com.dayker.viewed.presentation.navigation.screens.DetailsScreen

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

