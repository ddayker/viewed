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
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.GRAPH_NAME
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.NEW_MOVIE_ID_KEY
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.NOT_NEW
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.NOT_SAVED
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.SAVED_MOVIE_ID_KEY
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieScreen
import com.dayker.viewed.watched.feature.moviereview.presentation.MovieReviewScreen
import com.dayker.viewed.watched.feature.moviesearch.presentation.MovieSearchingScreen

fun NavGraphBuilder.watchedNavGraph(navController: NavHostController, windowSize: WindowSizeClass) {
    navigation(
        route = GRAPH_NAME,
        startDestination = WatchedScreen.WatchedMovieScreen.route
    ) {
        composable(
            route = WatchedScreen.WatchedMovieScreen.route + "/{$SAVED_MOVIE_ID_KEY}",
            arguments = listOf(
                navArgument(
                    name = SAVED_MOVIE_ID_KEY
                ) {
                    type = NavType.LongType
                    defaultValue = NOT_SAVED
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
            MovieReviewScreen(
                windowSize = windowSize,
                navController = navController
            )
        }
        composable(
            route = WatchedScreen.AddEditMovieScreen.route + "?${SAVED_MOVIE_ID_KEY}={$SAVED_MOVIE_ID_KEY}&${NEW_MOVIE_ID_KEY}={$NEW_MOVIE_ID_KEY}",
            arguments = listOf(
                navArgument(
                    name = SAVED_MOVIE_ID_KEY
                ) {
                    type = NavType.LongType
                    defaultValue = NOT_SAVED
                },
                navArgument(
                    name = NEW_MOVIE_ID_KEY
                ) {
                    type = NavType.StringType
                    defaultValue = NOT_NEW
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
    const val SAVED_MOVIE_ID_KEY = "movie_id"
    const val NEW_MOVIE_ID_KEY = "new_movie_id"
    const val NOT_SAVED = -1L
    const val NOT_NEW = ""
}