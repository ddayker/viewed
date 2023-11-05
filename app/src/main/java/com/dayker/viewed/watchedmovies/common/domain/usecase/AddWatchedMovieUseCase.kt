package com.dayker.viewed.watchedmovies.common.domain.usecase

import com.dayker.viewed.watchedmovies.common.domain.model.Movie
import com.dayker.viewed.watchedmovies.common.domain.repository.WatchedMoviesRepository

class AddWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(
        movie: Movie
    ) {
        repository.insertMovie(movie)
    }
}