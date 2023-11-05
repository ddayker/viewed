package com.dayker.viewed.watchedmovies.common.platform.datasource.movies

import com.dayker.viewed.watchedmovies.common.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalMoviesDataSource {
    fun getMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Long): Movie?
    suspend fun insertMovie(movie: Movie): Long
    suspend fun deleteMovie(movie: Movie)
}