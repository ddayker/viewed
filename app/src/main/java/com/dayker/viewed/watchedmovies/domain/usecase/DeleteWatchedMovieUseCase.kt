package com.dayker.viewed.watchedmovies.domain.usecase

import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.domain.repository.WatchedMoviesRepository

class DeleteWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(
        movie: Movie
    ) {
        repository.deleteMovie(movie)
    }
}