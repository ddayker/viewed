package com.dayker.viewed.watchedmovies.presentation.watchedlist

import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder
import com.dayker.viewed.watchedmovies.domain.util.OrderType

data class WatchedState(
    val movies: List<Movie> = emptyList(),
    val moviesOrder: MoviesOrder = MoviesOrder.Rating(OrderType.Descending),
    val isFABVisible: Boolean = true,
    val isOrderSelectionVisible: Boolean = false,
    val isSynchronized: Boolean = true
)