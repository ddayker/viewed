package com.dayker.viewed.watched.common.domain.usecase

import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository

class AddWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(
        movie: Movie
    ) {
        repository.insertMovie(movie)
    }
}