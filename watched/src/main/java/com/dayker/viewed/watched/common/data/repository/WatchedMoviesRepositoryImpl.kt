package com.dayker.viewed.watched.common.data.repository

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.dayker.viewed.authentication.domain.client.AuthClient
import com.dayker.viewed.authentication.domain.module.User
import com.dayker.viewed.watched.common.data.datasource.images.RemoteImageDataSource
import com.dayker.viewed.watched.common.data.datasource.watchedmovies.RemoteMoviesDataSource
import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watched.common.platform.datasource.images.LocalImageDataSource
import com.dayker.viewed.watched.common.platform.datasource.movies.LocalMoviesDataSource
import io.ktor.http.URLProtocol
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.FileNotFoundException

internal class WatchedMoviesRepositoryImpl(
    private val localMoviesDataSource: LocalMoviesDataSource,
    private val localImageDataSource: LocalImageDataSource,
    private val remoteMoviesDataSource: RemoteMoviesDataSource,
    private val remoteImageDataSource: RemoteImageDataSource,
    private val authClient: AuthClient,
    private val connectivityManager: ConnectivityManager,
    private val dispatcher: CoroutineDispatcher,
) : WatchedMoviesRepository {

    override fun getMovies(): Flow<List<Movie>> {
        val user = authClient.getSignedInUser()
        return if (user != null) {
            remoteMoviesDataSource.getMovies(user.userId)
        } else {
            localMoviesDataSource.getMovies()
        }
    }

    override suspend fun getMovieById(id: Long): Movie? {
        val user = authClient.getSignedInUser()
        return if (user != null) {
            remoteMoviesDataSource.getMovieById(id = id, userId = user.userId)
        } else {
            localMoviesDataSource.getMovieById(id)
        }
    }

    override suspend fun saveMovie(movie: Movie): Long {
        val user = authClient.getSignedInUser()
        return if (user != null) {
            val id = remoteMoviesDataSource.saveMovie(movie, user.userId)
            CoroutineScope(dispatcher).launch {
                saveImageRemotelyWhileNetworkActiveOrLocally(
                    movie = movie,
                    user = user,
                    newMovieId = id
                )
            }
            id
        } else {
            val id = localMoviesDataSource.insertMovie(movie)
            saveImageLocally(movie = movie, newMovieId = id)
            id
        }
    }

    override suspend fun deleteMovie(movie: Movie) {
        val user = authClient.getSignedInUser()
        if (user != null) {
            remoteMoviesDataSource.deleteMovie(movie, userId = user.userId)
            val isDeletedFromRemoteSource = remoteImageDataSource.deleteImage(uri = movie.imageURL)
            if (!isDeletedFromRemoteSource) {
                localImageDataSource.deleteImage(uri = movie.imageURL)
            }
        } else {
            localMoviesDataSource.deleteMovie(movie)
            localImageDataSource.deleteImage(uri = movie.imageURL)
        }
    }

    override suspend fun transferDataFromLocalToRemote() {
        val localMovies = localMoviesDataSource.getMovies().first()
        localMovies.onEach { localMovie ->
            saveMovie(localMovie)
        }
        clearLocalData()
    }

    override suspend fun clearLocalData() {
        val localMovies = localMoviesDataSource.getMovies().first()
        localMovies.onEach { localMovie ->
            localMoviesDataSource.deleteMovie(localMovie)
            localImageDataSource.deleteImage(uri = localMovie.imageURL)
        }
    }

    override suspend fun isUnsavedLocalData(): Boolean {
        val isLocalDataNotEmpty = localMoviesDataSource.getMovies().first().isNotEmpty()
        val isUserAuthenticated = authClient.getSignedInUser() != null
        return isLocalDataNotEmpty && isUserAuthenticated
    }


    @SuppressLint("MissingPermission")
    private suspend fun saveImageRemotelyWhileNetworkActiveOrLocally(
        movie: Movie,
        user: User,
        newMovieId: Long
    ) {
        if (isSavingRequired(movie.imageURL)) {
            val network = connectivityManager.activeNetwork
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)
            if (activeNetwork != null && (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            ) {
                val remoteImageUrl =
                    remoteImageDataSource.saveImage(movie.imageURL, user.userId, newMovieId)
                remoteMoviesDataSource.saveMovie(
                    movie.copy(imageURL = remoteImageUrl ?: "", id = newMovieId),
                    user.userId
                )
            } else {
                val imgFileName =
                    localImageDataSource.saveImage(uri = movie.imageURL, movieId = newMovieId)
                remoteMoviesDataSource.saveMovie(
                    movie.copy(imageURL = imgFileName, id = newMovieId),
                    user.userId
                )
            }
        }
    }

    private suspend fun saveImageLocally(movie: Movie, newMovieId: Long) {
        if (isSavingRequired(movie.imageURL)) {
            try {
                val imgFileName =
                    localImageDataSource.saveImage(uri = movie.imageURL, movieId = newMovieId)
                localMoviesDataSource.insertMovie(
                    movie.copy(
                        imageURL = imgFileName,
                        id = newMovieId
                    )
                )
            } catch (e: FileNotFoundException) {
                println(e)
            }
        }
    }

    private fun isSavingRequired(url: String): Boolean {
        return url.isNotBlank() && !url.startsWith(URLProtocol.HTTPS.name)
    }

}