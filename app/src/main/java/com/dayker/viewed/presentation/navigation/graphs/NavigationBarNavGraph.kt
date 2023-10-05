package com.dayker.viewed.presentation.navigation.graphs

import android.annotation.SuppressLint
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dayker.viewed.presentation.details.DetailsScreen
import com.dayker.viewed.presentation.discover.DiscoverScreen
import com.dayker.viewed.presentation.navigation.navanimations.intoLeftAnimation
import com.dayker.viewed.presentation.navigation.navanimations.intoRightAnimation
import com.dayker.viewed.presentation.navigation.navanimations.outLeftAnimation
import com.dayker.viewed.presentation.navigation.navanimations.outRightAnimation
import com.dayker.viewed.presentation.navigation.screens.DestinationScreen
import com.dayker.viewed.presentation.navigation.screens.DetailsScreen
import com.dayker.viewed.presentation.watched.WatchedScreen

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