package com.dayker.viewed.watchedmovies.presentation.watchedlist

import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder

sealed class WatchedScreenEvent {
    data class Order(val moviesOrder: MoviesOrder) : WatchedScreenEvent()
    object ToggleOrderSection : WatchedScreenEvent()
    object ShowFAB : WatchedScreenEvent()
    object HideFAB : WatchedScreenEvent()
    object ExtendFAB : WatchedScreenEvent()

}