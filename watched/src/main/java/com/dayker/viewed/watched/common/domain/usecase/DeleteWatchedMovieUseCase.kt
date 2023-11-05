package com.dayker.viewed.watched.common.domain.usecase

import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository

class DeleteWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(
        movie: Movie
    ) {
        repository.deleteMovie(movie)
    }
}