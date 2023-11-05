package com.dayker.viewed.app.navigation.graphs

import android.annotation.SuppressLint
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dayker.viewed.app.navigation.screens.DestinationScreen
import com.dayker.viewed.core.ui.navanimations.intoLeftAnimation
import com.dayker.viewed.core.ui.navanimations.intoRightAnimation
import com.dayker.viewed.core.ui.navanimations.outLeftAnimation
import com.dayker.viewed.core.ui.navanimations.outRightAnimation
import com.dayker.viewed.details.common.navigation.detailsNavGraph
import com.dayker.viewed.details.feature.details.DetailsScreen
import com.dayker.viewed.network.common.navigation.networkNavGraph
import com.dayker.viewed.network.feature.network.NetworkScreen
import com.dayker.viewed.watched.common.platform.navigation.watchedNavGraph
import com.dayker.viewed.watched.feature.movies.presentation.WatchedMoviesScreen

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
                    DestinationScreen.Network.route, DestinationScreen.Details.route -> {
                        intoLeftAnimation()
                    }

                    else -> null
                }
            },
            popEnterTransition = {
                when (navController.previousBackStackEntry?.destination?.route) {
                    DestinationScreen.Network.route, DestinationScreen.Details.route -> {
                        intoLeftAnimation()
                    }

                    else -> null
                }
            },
            exitTransition = {
                when (navController.currentDestination?.route) {
                    DestinationScreen.Network.route, DestinationScreen.Details.route -> {
                        outRightAnimation()
                    }

                    else -> null
                }
            },
            popExitTransition = {
                when (navController.currentDestination?.route) {
                    DestinationScreen.Network.route, DestinationScreen.Details.route -> {
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
            route = DestinationScreen.Network.route,
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
            NetworkScreen(
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
        networkNavGraph(navController = navController, windowSize = windowSize)
    }
}