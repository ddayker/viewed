package com.dayker.viewed.watchedmovies.data.repository

import com.dayker.viewed.watchedmovies.data.source.MovieDao
import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.domain.repository.WatchedMoviesRepository
import kotlinx.coroutines.flow.Flow

class WatchedMoviesRepositoryImpl(
    private val dao: MovieDao
) : WatchedMoviesRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies()
    }

    override suspend fun getMovieById(id: Long): Movie? {
        return dao.getMovieById(id)
    }

    override suspend fun insertMovie(movie: Movie): Long? {
        return dao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        return dao.deleteMovie(movie)
    }

}