package com.dayker.viewed.details.common.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dayker.viewed.core.ui.navanimations.scaleInAnimation
import com.dayker.viewed.core.ui.navanimations.scaleOutAnimation
import com.dayker.viewed.details.common.navigation.DetailsNavGraphConstants.GRAPH_NAME
import com.dayker.viewed.details.feature.aboutapp.presentation.AboutAppScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController, windowSize: WindowSizeClass) {
    navigation(
        route = GRAPH_NAME,
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


object DetailsNavGraphConstants {
    const val GRAPH_NAME = "details_graph"
}

