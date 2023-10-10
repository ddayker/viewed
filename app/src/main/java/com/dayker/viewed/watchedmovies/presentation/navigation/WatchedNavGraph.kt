package com.dayker.viewed.watchedmovies.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dayker.viewed.app.core.presentation.navigation.graphs.Graph
import com.dayker.viewed.app.core.presentation.navigation.navanimations.scaleInAnimation
import com.dayker.viewed.app.core.presentation.navigation.navanimations.scaleOutAnimation
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.AddEditMovieScreen
import com.dayker.viewed.watchedmovies.presentation.watchedmovie.WatchedMovieScreen

fun NavGraphBuilder.watchedNavGraph(navController: NavHostController, windowSize: WindowSizeClass) {
    navigation(
        route = Graph.WATCHED,
        startDestination = WatchedScreen.WatchedMovieScreen.route
    ) {
        composable(
            route = WatchedScreen.WatchedMovieScreen.route,
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
            WatchedMovieScreen(
                windowSize = windowSize,
                navController = navController
            )
        }
        composable(
            route = WatchedScreen.AddEditMovieScreen.route,
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
            AddEditMovieScreen(
                windowSize = windowSize,
                navController = navController
            )
        }
    }
}