package com.dayker.viewed.watched.common.platform.datasource.movies

import com.dayker.viewed.watched.common.domain.model.Movie
import kotlinx.coroutines.flow.Flow

internal interface LocalMoviesDataSource {
    fun getMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Long): Movie?
    suspend fun insertMovie(movie: Movie): Long
    suspend fun deleteMovie(movie: Movie)
}