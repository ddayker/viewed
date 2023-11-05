package com.dayker.viewed.watched.common.platform.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.dayker.viewed.core.ui.navanimations.scaleInAnimation
import com.dayker.viewed.core.ui.navanimations.scaleOutAnimation
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.EMPTY_ID
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.GRAPH_NAME
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.MOVIE_ID_KEY
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieScreen
import com.dayker.viewed.watched.feature.movieinfo.presentation.WatchedMovieScreen
import com.dayker.viewed.watched.feature.moviesearch.presentation.MovieSearchingScreen

fun NavGraphBuilder.watchedNavGraph(navController: NavHostController, windowSize: WindowSizeClass) {
    navigation(
        route = GRAPH_NAME,
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
            route = WatchedScreen.AddEditMovieScreen.route + "/{$MOVIE_ID_KEY}",
            arguments = listOf(
                navArgument(
                    name = MOVIE_ID_KEY
                ) {
                    type = NavType.LongType
                    defaultValue = EMPTY_ID
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
            AddEditMovieScreen(
                windowSize = windowSize,
                navController = navController
            )
        }
        composable(
            route = WatchedScreen.SearchScreen.route,
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
    }
}

object WatchedNavGraphConstants {
    const val GRAPH_NAME = "watched_graph"
    const val MOVIE_ID_KEY = "movie_id"
    const val EMPTY_ID = -1L
}