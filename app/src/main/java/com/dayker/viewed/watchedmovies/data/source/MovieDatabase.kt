package com.dayker.viewed.watchedmovies.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dayker.viewed.watchedmovies.domain.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movies_db"
    }

}