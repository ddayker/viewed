package com.dayker.viewed.watchedmovies.feature.movies.presentation

import com.dayker.viewed.watchedmovies.common.domain.model.Movie
import com.dayker.viewed.watchedmovies.common.domain.util.MoviesOrder
import com.dayker.viewed.watchedmovies.common.domain.util.OrderType

data class WatchedMoviesState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val moviesOrder: MoviesOrder = MoviesOrder.Rating(OrderType.Descending),
    val isFABVisible: Boolean = true,
    val isFABExtended: Boolean = false,
    val isOrderSelectionVisible: Boolean = false,
    val isSynchronized: Boolean = true
)