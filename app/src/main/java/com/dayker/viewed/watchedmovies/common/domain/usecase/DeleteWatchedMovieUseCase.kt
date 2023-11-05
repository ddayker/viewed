package com.dayker.viewed.watchedmovies.common.domain.usecase

import com.dayker.viewed.watchedmovies.common.domain.model.Movie
import com.dayker.viewed.watchedmovies.common.domain.repository.WatchedMoviesRepository

class DeleteWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(
        movie: Movie
    ) {
        repository.deleteMovie(movie)
    }
}