package com.dayker.viewed.watched.common.platform.datasource.movies.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dayker.viewed.watched.common.platform.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE id = :id")
    suspend fun getMovieById(id: Long): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity): Long

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

}