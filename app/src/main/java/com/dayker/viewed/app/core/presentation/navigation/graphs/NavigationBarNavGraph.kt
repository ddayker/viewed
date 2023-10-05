package com.dayker.viewed.app.core.presentation.navigation.graphs

import android.annotation.SuppressLint
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dayker.viewed.app.core.presentation.navigation.navanimations.intoLeftAnimation
import com.dayker.viewed.app.core.presentation.navigation.navanimations.intoRightAnimation
import com.dayker.viewed.app.core.presentation.navigation.navanimations.outLeftAnimation
import com.dayker.viewed.app.core.presentation.navigation.navanimations.outRightAnimation
import com.dayker.viewed.app.core.presentation.navigation.screens.DestinationScreen
import com.dayker.viewed.details.presentation.DetailsScreen
import com.dayker.viewed.details.presentation.navigation.DetailsScreen
import com.dayker.viewed.details.presentation.navigation.detailsNavGraph
import com.dayker.viewed.discovermovies.presentation.DiscoverScreen
import com.dayker.viewed.watchedmovies.presentation.watchedlist.WatchedScreen

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
                intoLeftAnimation()
            },
            popEnterTransition = {
                intoLeftAnimation()
            },
            exitTransition = {
                outRightAnimation()
            },
            popExitTransition = {
                outRightAnimation()
            }
        ) {
            WatchedScreen(
                modifier = modifier
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
                name = DestinationScreen.Discover.route,
                onClick = { }
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
                onAboutAppClicked = {
                    navController.navigate(DetailsScreen.AboutApp.route)
                }
            )
        }
        detailsNavGraph(navController = navController, windowSize = windowSize)
    }
}