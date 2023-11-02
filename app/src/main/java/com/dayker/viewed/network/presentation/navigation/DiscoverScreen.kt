package com.dayker.viewed.network.presentation.navigation

sealed class DiscoverScreen(val route: String) {
    object SearchScreen : DiscoverScreen(route = "search_screen")
    object MovieInfoScreen : DiscoverScreen(route = "movie_info_screen")
}