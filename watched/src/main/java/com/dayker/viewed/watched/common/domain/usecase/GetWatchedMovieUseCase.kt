package com.dayker.viewed.watched.common.domain.usecase

import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository

class GetWatchedMovieUseCase(
    private val repository: WatchedMoviesRepository
) {
    suspend operator fun invoke(id: Long) = repository.getMovieById(id)
}