package com.dayker.viewed.core.presentation.navigation.graphs

import android.annotation.SuppressLint
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dayker.viewed.core.presentation.navigation.navanimations.intoLeftAnimation
import com.dayker.viewed.core.presentation.navigation.navanimations.intoRightAnimation
import com.dayker.viewed.core.presentation.navigation.navanimations.outLeftAnimation
import com.dayker.viewed.core.presentation.navigation.navanimations.outRightAnimation
import com.dayker.viewed.core.presentation.navigation.screens.DestinationScreen
import com.dayker.viewed.details.presentation.DetailsScreen
import com.dayker.viewed.details.presentation.navigation.detailsNavGraph
import com.dayker.viewed.network.presentation.discover.DiscoverScreen
import com.dayker.viewed.network.presentation.navigation.discoverNavGraph
import com.dayker.viewed.watchedmovies.common.platform.navigation.watchedNavGraph
import com.dayker.viewed.watchedmovies.feature.movies.presentation.WatchedMoviesScreen

@SuppressLint("RestrictedApi")
@Composable
fun NavigationBarNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    windowSize: WindowSizeClass
) {
    NavHost(
        navController = navController,
        route = Graph.NAVIGATION_BAR,
        startDestination = DestinationScreen.Watched.route
    ) {
        composable(
            route = DestinationScreen.Watched.route,
            enterTransition = {
                when (navController.previousBackStackEntry?.destination?.route) {
                    DestinationScreen.Discover.route, DestinationScreen.Details.route -> {
                        intoLeftAnimation()
                    }

                    else -> null
                }
            },
            popEnterTransition = {
                when (navController.previousBackStackEntry?.destination?.route) {
                    DestinationScreen.Discover.route, DestinationScreen.Details.route -> {
                        intoLeftAnimation()
                    }

                    else -> null
                }
            },
            exitTransition = {
                when (navController.currentDestination?.route) {
                    DestinationScreen.Discover.route, DestinationScreen.Details.route -> {
                        outRightAnimation()
                    }

                    else -> null
                }
            },
            popExitTransition = {
                when (navController.currentDestination?.route) {
                    DestinationScreen.Discover.route, DestinationScreen.Details.route -> {
                        outRightAnimation()
                    }

                    else -> null
                }
            }
        ) {
            WatchedMoviesScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(
            route = DestinationScreen.Discover.route,
            enterTransition = {
                when (navController.previousBackStackEntry?.destination?.route) {
                    DestinationScreen.Watched.route -> {
                        intoRightAnimation()
                    }

                    DestinationScreen.Details.route -> {
                        intoLeftAnimation()
                    }

                    else -> {
                        null
                    }
                }
            },
            popEnterTransition = {
                when (navController.previousBackStackEntry?.destination?.route) {
                    DestinationScreen.Watched.route -> {
                        intoRightAnimation()
                    }

                    DestinationScreen.Details.route -> {
                        intoLeftAnimation()
                    }

                    else -> {
                        null
                    }
                }
            },
            exitTransition = {
                when (navController.currentDestination?.route) {
                    DestinationScreen.Watched.route -> {
                        outLeftAnimation()
                    }

                    DestinationScreen.Details.route -> {
                        outRightAnimation()
                    }

                    else -> {
                        null
                    }
                }
            },
            popExitTransition = {
                when (navController.currentDestination?.route) {
                    DestinationScreen.Watched.route -> {
                        outLeftAnimation()
                    }

                    DestinationScreen.Details.route -> {
                        outRightAnimation()
                    }

                    else -> {
                        null
                    }
                }
            }
        ) {
            DiscoverScreen(
                modifier = modifier,
                navController = navController
            )
        }
        composable(
            route = DestinationScreen.Details.route,
            enterTransition = {
                intoRightAnimation()
            },
            popEnterTransition = {
                intoRightAnimation()
            },
            exitTransition = {
                outLeftAnimation()
            },
            popExitTransition = {
                outLeftAnimation()
            }
        ) {
            DetailsScreen(
                modifier = modifier,
                navController = navController
            )
        }
        detailsNavGraph(navController = navController, windowSize = windowSize)
        watchedNavGraph(navController = navController, windowSize = windowSize)
        discoverNavGraph(navController = navController, windowSize = windowSize)
    }
}