package com.dayker.viewed.watched.common.data.datasource.watchedmovies.remote

import com.dayker.viewed.watched.common.data.datasource.watchedmovies.RemoteMoviesDataSource
import com.dayker.viewed.watched.common.data.datasource.watchedmovies.remote.Routes.USERS_DIR
import com.dayker.viewed.watched.common.data.datasource.watchedmovies.remote.Routes.USER_MOVIES_DIR
import com.dayker.viewed.watched.common.domain.model.Movie
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

internal class FirestoreMoviesDataSource(
    private val firestore: FirebaseFirestore,
) : RemoteMoviesDataSource {

    companion object {
        const val ID_FIELD = "id"
    }

    override fun getMovies(userId: String): Flow<List<Movie>> = callbackFlow {
        val userMoviesCollection = firestore.collection(USERS_DIR)
            .document(userId)
            .collection(USER_MOVIES_DIR)
        val listener = userMoviesCollection.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                trySend(emptyList()).isSuccess
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val movies = snapshot.documents.mapNotNull { it.toObject(Movie::class.java) }
                trySend(movies).isSuccess
            } else {
                trySend(emptyList()).isSuccess
            }
        }
        awaitClose { listener.remove() }
    }

    override suspend fun getMovieById(id: Long, userId: String): Movie? {
        return try {
            val userMoviesCollection = firestore.collection(USERS_DIR)
                .document(userId)
                .collection(USER_MOVIES_DIR)
            val querySnapshot = getQuerySnapshotForMovieId(userMoviesCollection, id)
            val movie = querySnapshot.documents.firstOrNull()?.toObject(Movie::class.java)
            movie
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveMovie(movie: Movie, userId: String): Long {
        var newMovie = movie
        try {
            val userMoviesCollection = firestore.collection(USERS_DIR)
                .document(userId)
                .collection(USER_MOVIES_DIR)
            if (movie.id == null) {
                newMovie = movie.copy(id = System.currentTimeMillis())
                userMoviesCollection.add(newMovie)
            } else {
                val querySnapshot = getQuerySnapshotForMovieId(userMoviesCollection, movie.id)
                if (!querySnapshot.isEmpty) {
                    val documentId = querySnapshot.firstOrNull()?.id
                    documentId?.let { userMoviesCollection.document(it).set(movie) }
                } else {
                    newMovie = movie.copy(id = System.currentTimeMillis())
                    userMoviesCollection.add(newMovie)
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
        return newMovie.id ?: -1L
    }

    override suspend fun deleteMovie(movie: Movie, userId: String) {
        try {
            val userMoviesCollection = firestore.collection(USERS_DIR)
                .document(userId)
                .collection(USER_MOVIES_DIR)
            val querySnapshot = getQuerySnapshotForMovieId(userMoviesCollection, movie.id)
            querySnapshot.documents.firstOrNull()?.let { document ->
                val documentId = document.id
                userMoviesCollection.document(documentId).delete()
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private suspend fun getQuerySnapshotForMovieId(
        userMoviesCollection: CollectionReference,
        movieId: Long?
    ): QuerySnapshot {
        return userMoviesCollection
            .whereEqualTo(ID_FIELD, movieId)
            .limit(1)
            .get()
            .await()
    }
}