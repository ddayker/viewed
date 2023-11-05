package com.dayker.viewed.watchedmovies.common.platform.navigation

sealed class WatchedScreen(val route: String) {
    object WatchedMovieScreen : WatchedScreen(route = "watched_movie_screen")
    object AddEditMovieScreen : WatchedScreen(route = "add_edit_movie_screen")
    object SearchScreen : WatchedScreen(route = "search_movie_screen")
}