package com.dayker.viewed.watchedmovies.di

import android.content.ContentResolver
import androidx.room.Room
import com.dayker.viewed.watchedmovies.data.repository.ImageCachingRepositoryImpl
import com.dayker.viewed.watchedmovies.data.repository.WatchedMoviesRepositoryImpl
import com.dayker.viewed.watchedmovies.data.source.MovieDao
import com.dayker.viewed.watchedmovies.data.source.MovieDatabase
import com.dayker.viewed.watchedmovies.domain.repository.ImageCachingRepository
import com.dayker.viewed.watchedmovies.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watchedmovies.domain.usecase.AddWatchedMovieUseCase
import com.dayker.viewed.watchedmovies.domain.usecase.DeleteWatchedMovieUseCase
import com.dayker.viewed.watchedmovies.domain.usecase.GetWatchedMovieUseCase
import com.dayker.viewed.watchedmovies.domain.usecase.GetWatchedMoviesUseCase
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.AddEditMovieViewModel
import com.dayker.viewed.watchedmovies.presentation.watchedlist.WatchedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import java.io.File

val watchedModule = module {

    viewModel<AddEditMovieViewModel> {
        AddEditMovieViewModel(
            addMovie = get(),
            deleteMovie = get(),
            getMovie = get(),
            savedStateHandle = get(),
            imageCachingRepository = get()
        )
    }

    viewModelOf(::AddEditMovieViewModel)

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

    single<WatchedMoviesRepository> {
        WatchedMoviesRepositoryImpl(dao = get())
    }

    single<GetWatchedMoviesUseCase> {
        GetWatchedMoviesUseCase(repository = get())
    }

    single<GetWatchedMovieUseCase> {
        GetWatchedMovieUseCase(repository = get())
    }

    single<AddWatchedMovieUseCase> {
        AddWatchedMovieUseCase(repository = get())
    }

    single<DeleteWatchedMovieUseCase> {
        DeleteWatchedMovieUseCase(repository = get())
    }

    viewModel<WatchedViewModel> {
        WatchedViewModel(getWatchedMoviesUseCase = get())
    }

    single<File> {
        androidApplication().filesDir
    }

    single<ContentResolver> {
        androidApplication().contentResolver
    }

    single<ImageCachingRepository> {
        ImageCachingRepositoryImpl(
            filesDir = get(),
            contentResolver = get()
        )
    }

}