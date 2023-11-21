package com.dayker.viewed.watched.common.domain.repository

import com.dayker.viewed.watched.common.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface WatchedMoviesRepository {
    fun getMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Long): Movie?
    suspend fun saveMovie(movie: Movie): Long
    suspend fun transferDataFromLocalToRemote()
    suspend fun isUnsavedLocalData(): Boolean
    suspend fun deleteMovie(movie: Movie)
    suspend fun clearLocalData()
}