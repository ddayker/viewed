package com.dayker.viewed.watchedmovies.domain.usecase

import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder
import com.dayker.viewed.watchedmovies.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWatchedMoviesUseCase(
    private val repository: WatchedMoviesRepository
) {
    operator fun invoke(
        order: MoviesOrder = MoviesOrder.Rating(OrderType.Descending)
    ): Flow<List<Movie>> {
        return repository.getMovies().map { movies ->
            when (order.orderType) {
                OrderType.Ascending -> {
                    when (order) {
                        is MoviesOrder.Rating -> {
                            movies.sortedBy { it.rating }
                        }

                        is MoviesOrder.Release -> {
                            movies.sortedBy { it.releaseDate }
                        }

                        is MoviesOrder.Viewing -> {
                            movies.sortedBy { it.viewingDate }
                        }
                    }
                }

                OrderType.Descending -> {
                    when (order) {
                        is MoviesOrder.Rating -> {
                            movies.sortedByDescending { it.rating }
                        }

                        is MoviesOrder.Release -> {
                            movies.sortedByDescending { it.releaseDate }
                        }

                        is MoviesOrder.Viewing -> {
                            movies.sortedByDescending { it.viewingDate }
                        }
                    }
                }
            }
        }
    }
}