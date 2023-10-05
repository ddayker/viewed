package com.dayker.viewed.watchedmovies.presentation.watchedlist

import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder

sealed class WatchedScreenEvent {
    data class Order(val noteOrder: MoviesOrder) : WatchedScreenEvent()
    object ToggleOrderSection : WatchedScreenEvent()
    object ShowFAB : WatchedScreenEvent()
    object HideFab : WatchedScreenEvent()

}