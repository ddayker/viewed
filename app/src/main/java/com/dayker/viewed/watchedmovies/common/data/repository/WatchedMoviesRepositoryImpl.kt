package com.dayker.viewed.watchedmovies.common.data.repository

import com.dayker.viewed.watchedmovies.common.domain.model.Movie
import com.dayker.viewed.watchedmovies.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watchedmovies.common.platform.datasource.images.ImageDataSource
import com.dayker.viewed.watchedmovies.common.platform.datasource.movies.LocalMoviesDataSource
import kotlinx.coroutines.flow.Flow

class WatchedMoviesRepositoryImpl(
    private val localMoviesDataSource: LocalMoviesDataSource,
    private val imageDataSource: ImageDataSource
) : WatchedMoviesRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return localMoviesDataSource.getMovies()
    }

    override suspend fun getMovieById(id: Long): Movie? {
        return localMoviesDataSource.getMovieById(id)
    }

    override suspend fun insertMovie(movie: Movie) {
        val id = movie.id ?: localMoviesDataSource.insertMovie(movie)
        val imgFileName = if (movie.imageURL != "") imageDataSource.saveImage(
            uri = movie.imageURL,
            movieId = id
        ) else ""
        val newMovie = movie.copy(imageURL = imgFileName, id = id)
        localMoviesDataSource.insertMovie(newMovie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        localMoviesDataSource.deleteMovie(movie)
        imageDataSource.deleteImage(uri = movie.imageURL)
    }
}