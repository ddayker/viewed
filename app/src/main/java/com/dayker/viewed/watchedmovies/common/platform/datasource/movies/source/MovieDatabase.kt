package com.dayker.viewed.watchedmovies.common.platform.datasource.movies.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dayker.viewed.watchedmovies.common.platform.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movies_database"
    }

}