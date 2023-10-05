package com.dayker.viewed.domain.usecase

import com.dayker.viewed.domain.model.Movie
import com.dayker.viewed.domain.util.MoviesOrder
import com.dayker.viewed.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetWatchedMoviesUseCase() {
    operator fun invoke(
        moviesOrder: MoviesOrder = MoviesOrder.Rating(OrderType.Descending)
    ): Flow<List<Movie>> {
        val movie = Movie(
            title = "Openheimer",
            duration = 120,
            rating = 8.2,
            viewingDate = "October 10, 2023",
            releaseDate = "",
            review = "I liked this movie. I think it's ine of the best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best",
        )
        val movie2 = Movie(
            title = "Spider Man",
            duration = 130,
            rating = 7.2,
            viewingDate = "September 11, 2023",
            releaseDate = "",
            review = "I liked this movie. I think it's ine of the best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best",
        )
        val movie3 = Movie(
            title = "Barbie",
            duration = 130,
            rating = 6.2,
            viewingDate = "March 20, 2023",
            releaseDate = "",
            review = "I liked this movie. I think it's ine of the best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best best",
        )
        val list = listOf<Movie>(
            movie2,
            movie,
            movie3,
            movie,
            movie3,
            movie,
            movie2,
            movie,
            movie,
            movie3,
            movie2,
            movie,
        )
        return flowOf(list)
    }
}