package com.dayker.viewed.watchedmovies.common.domain.repository

import com.dayker.viewed.watchedmovies.common.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface WatchedMoviesRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Long): Movie?
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}