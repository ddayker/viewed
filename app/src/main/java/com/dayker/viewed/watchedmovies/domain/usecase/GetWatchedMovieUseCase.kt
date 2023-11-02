package com.dayker.viewed.watchedmovies.domain.usecase

import com.dayker.viewed.watchedmovies.domain.repository.WatchedMoviesRepository

class GetWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(id: Long) = repository.getMovieById(id)
}