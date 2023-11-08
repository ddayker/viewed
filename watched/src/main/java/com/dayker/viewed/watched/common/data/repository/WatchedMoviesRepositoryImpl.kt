package com.dayker.viewed.watched.common.data.repository

import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watched.common.platform.datasource.images.ImageDataSource
import com.dayker.viewed.watched.common.platform.datasource.movies.LocalMoviesDataSource
import io.ktor.http.URLProtocol
import kotlinx.coroutines.flow.Flow
import java.io.FileNotFoundException

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

    /**
     * Insert a movie into the local database and, if applicable, save an image associated with it.
     *
     * Images that were selected using photoPickerLauncher should be saved with a permanent name,
     * since after restarting the application will no longer have rights to read the selected uri.
     * (the image that was uploaded from the api does not need to be saved)
     *
     */
    override suspend fun insertMovie(movie: Movie): Long {
        val id = localMoviesDataSource.insertMovie(movie)
        if (movie.imageURL != "" && !movie.imageURL.startsWith(URLProtocol.HTTPS.name)) {
            try {
                val imgFileName = imageDataSource.saveImage(uri = movie.imageURL, movieId = id)
                localMoviesDataSource.insertMovie(movie.copy(imageURL = imgFileName, id = id))
            } catch (e: FileNotFoundException) {
                println(e)
            }
        }
        return id
    }

    override suspend fun deleteMovie(movie: Movie) {
        localMoviesDataSource.deleteMovie(movie)
        imageDataSource.deleteImage(uri = movie.imageURL)
    }
}