package com.dayker.viewed.watched.common.platform.di

import android.content.ContentResolver
import androidx.room.Room
import com.dayker.viewed.watched.common.platform.datasource.images.AndroidImageDataSource
import com.dayker.viewed.watched.common.platform.datasource.images.LocalImageDataSource
import com.dayker.viewed.watched.common.platform.datasource.movies.DatabaseMoviesDataSource
import com.dayker.viewed.watched.common.platform.datasource.movies.LocalMoviesDataSource
import com.dayker.viewed.watched.common.platform.datasource.movies.source.MovieDao
import com.dayker.viewed.watched.common.platform.datasource.movies.source.MovieDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.io.File

val watchedPlatformModule = module {

    single<MovieDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            MovieDatabase.DATABASE_NAME
        ).build()
    }

    single<MovieDao> {
        val database = get<MovieDatabase>()
        database.getMovieDao()
    }

    singleOf(::DatabaseMoviesDataSource) {
        bind<LocalMoviesDataSource>()
    }

    singleOf(::AndroidImageDataSource) {
        bind<LocalImageDataSource>()
    }

    single<File> {
        androidApplication().filesDir
    }

    single<ContentResolver> {
        androidApplication().contentResolver
    }
}