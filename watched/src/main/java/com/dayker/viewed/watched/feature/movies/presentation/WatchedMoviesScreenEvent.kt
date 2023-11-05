package com.dayker.viewed.watched.feature.movies.presentation

import com.dayker.viewed.watched.common.domain.util.MoviesOrder

sealed class WatchedMoviesScreenEvent {
    data class Order(val moviesOrder: MoviesOrder) : WatchedMoviesScreenEvent()
    object ToggleOrderSection : WatchedMoviesScreenEvent()
    object ShowFAB : WatchedMoviesScreenEvent()
    object HideFAB : WatchedMoviesScreenEvent()
    object ExtendFAB : WatchedMoviesScreenEvent()

}