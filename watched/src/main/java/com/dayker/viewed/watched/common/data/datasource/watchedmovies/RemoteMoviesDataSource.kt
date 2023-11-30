package com.dayker.viewed.watched.common.data.datasource.watchedmovies

import com.dayker.viewed.watched.common.domain.model.Movie
import kotlinx.coroutines.flow.Flow

internal interface RemoteMoviesDataSource {

    fun getMovies(userId: String): Flow<List<Movie>>
    suspend fun getMovieById(id: Long, userId: String): Movie?
    suspend fun saveMovie(movie: Movie, userId: String): Long
    suspend fun deleteMovie(movie: Movie, userId: String)
}