package com.dayker.viewed.watchedmovies.domain.usecase

import com.dayker.viewed.common.Resource
import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder
import com.dayker.viewed.watchedmovies.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class GetWatchedMoviesUseCase(
    private val repository: WatchedMoviesRepository
) {
    operator fun invoke(
        order: MoviesOrder = MoviesOrder.Rating(OrderType.Descending)
    ): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        repository.getMovies().map { movies ->
            val savedMovies = when (order.orderType) {
                OrderType.Ascending -> {
                    when (order) {
                        is MoviesOrder.Rating -> movies.sortedBy { it.rating }
                        is MoviesOrder.Release -> movies.sortedBy { it.releaseDate }
                        is MoviesOrder.Viewing -> movies.sortedBy { it.viewingDate }
                    }
                }

                OrderType.Descending -> {
                    when (order) {
                        is MoviesOrder.Rating -> movies.sortedByDescending { it.rating }
                        is MoviesOrder.Release -> movies.sortedByDescending { it.releaseDate }
                        is MoviesOrder.Viewing -> movies.sortedByDescending { it.viewingDate }
                    }
                }
            }
            emit(Resource.Success(data = savedMovies))
        }
            .catch { e ->
                emit(Resource.Error(e.message))
            }
            .collect()
    }
}
