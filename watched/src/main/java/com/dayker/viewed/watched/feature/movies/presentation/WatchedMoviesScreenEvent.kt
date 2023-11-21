package com.dayker.viewed.watched.feature.movies.presentation

import com.dayker.viewed.watched.common.domain.util.MoviesOrder

sealed class WatchedMoviesScreenEvent {
    data class Order(val moviesOrder: MoviesOrder) : WatchedMoviesScreenEvent()
    object ToggleOrderSection : WatchedMoviesScreenEvent()
    object ShowFAB : WatchedMoviesScreenEvent()
    object HideFAB : WatchedMoviesScreenEvent()
    object ExtendFAB : WatchedMoviesScreenEvent()
    object AddManuallyClicked : WatchedMoviesScreenEvent()
    object AddBySearchClicked : WatchedMoviesScreenEvent()
    object AddToAccountClicked : WatchedMoviesScreenEvent()
    object DeleteLocalClicked : WatchedMoviesScreenEvent()
    data class MovieClicked(val id: Long) : WatchedMoviesScreenEvent()

}