package com.dayker.viewed.presentation.navigation.screens

sealed class WatchedScreen(val route: String) {
    object WatchedMovieScreen : WatchedScreen(route = "watched_movie_screen")
}