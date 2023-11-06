package com.dayker.viewed.watched.feature.movies.presentation

sealed class WatchedMoviesScreenAction {
    object OpenManuallyAdding : WatchedMoviesScreenAction()
    object OpenSearch : WatchedMoviesScreenAction()
    data class OpenMovieInfo(val id: Long) : WatchedMoviesScreenAction()
}