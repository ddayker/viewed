package com.dayker.viewed.presentation.watched

import com.dayker.viewed.domain.util.MoviesOrder

sealed class WatchedScreenEvent {
    data class Order(val noteOrder: MoviesOrder) : WatchedScreenEvent()
    object ToggleOrderSection : WatchedScreenEvent()
    object ShowFAB : WatchedScreenEvent()
    object HideFab : WatchedScreenEvent()

}