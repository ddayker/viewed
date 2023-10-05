package com.dayker.viewed.presentation.watched

import com.dayker.viewed.domain.model.Movie
import com.dayker.viewed.domain.util.MoviesOrder
import com.dayker.viewed.domain.util.OrderType

data class WatchedState(
    val movies: List<Movie> = emptyList(),
    val moviesOrder: MoviesOrder = MoviesOrder.Rating(OrderType.Descending),
    val isFABVisible: Boolean = true,
    val isOrderSelectionVisible: Boolean = false,
    val isSynchronized: Boolean = true
)