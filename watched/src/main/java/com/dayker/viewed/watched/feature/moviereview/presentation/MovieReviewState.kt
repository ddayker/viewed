package com.dayker.viewed.watched.feature.moviereview.presentation

import com.dayker.viewed.watched.common.domain.model.Movie

data class MovieReviewState(
    val isLoading: Boolean = true,
    val movie: Movie = Movie()
)
