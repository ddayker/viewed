package com.dayker.viewed.watched.feature.movies.presentation

import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.util.MoviesOrder
import com.dayker.viewed.watched.common.domain.util.OrderType

data class WatchedMoviesState(
    val isUnsavedLocalData: Boolean = false,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val moviesOrder: MoviesOrder = MoviesOrder.Rating(OrderType.Descending),
    val isFABVisible: Boolean = true,
    val isFABExtended: Boolean = false,
    val isOrderSelectionVisible: Boolean = false,
)