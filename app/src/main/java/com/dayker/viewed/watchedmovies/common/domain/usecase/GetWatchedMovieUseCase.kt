package com.dayker.viewed.watchedmovies.common.domain.usecase

import com.dayker.viewed.watchedmovies.common.domain.repository.WatchedMoviesRepository

class GetWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(id: Long) = repository.getMovieById(id)
}