package com.dayker.viewed.watchedmovies.feature.movies.di

import com.dayker.viewed.watchedmovies.feature.movies.presentation.WatchedMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val watchedMoviesModule = module {
    viewModelOf(::WatchedMoviesViewModel)
}