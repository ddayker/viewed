package com.dayker.viewed.watchedmovies.domain.repository

import com.dayker.viewed.watchedmovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface WatchedMoviesRepository {

    fun getMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Long): Movie?
    suspend fun insertMovie(movie: Movie): Long?
    suspend fun deleteMovie(movie: Movie)
}