package com.dayker.viewed.network.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dayker.viewed.common.presentation.navigation.graphs.Graph
import com.dayker.viewed.common.presentation.navigation.navanimations.scaleInAnimation
import com.dayker.viewed.common.presentation.navigation.navanimations.scaleOutAnimation
import com.dayker.viewed.network.presentation.navigation.DiscoverNavGraphConstants.MOVIE_ID_KEY
import com.dayker.viewed.searchmovie.presentation.MovieSearchingScreen

fun NavGraphBuilder.discoverNavGraph(
    navController: NavHostController,
    windowSize: WindowSizeClass
) {
    navigation(
        route = Graph.DISCOVER,
        startDestination = DiscoverScreen.SearchScreen.route
    ) {
        composable(
            route = DiscoverScreen.SearchScreen.route,
            enterTransition = {
                null
            },
            popEnterTransition = {
                null
            },
            exitTransition = {
                null
            },
            popExitTransition = {
                null
            }
        ) {
            MovieSearchingScreen(
                navController = navController,
                windowSize = windowSize,
            )
        }
        composable(
            route = DiscoverScreen.MovieInfoScreen.route + "/{$MOVIE_ID_KEY}",
            arguments = listOf(
                navArgument(
                    name = MOVIE_ID_KEY
                ) {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            ),
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

        }
    }
}

object DiscoverNavGraphConstants {
    const val MOVIE_ID_KEY = "movie_id"
}