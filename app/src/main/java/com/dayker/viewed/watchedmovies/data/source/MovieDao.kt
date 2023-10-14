package com.dayker.viewed.watchedmovies.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dayker.viewed.watchedmovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie): Long

    @Delete
    suspend fun deleteMovie(movie: Movie)

}