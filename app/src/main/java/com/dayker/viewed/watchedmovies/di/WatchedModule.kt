package com.dayker.viewed.watchedmovies.di

import com.dayker.viewed.watchedmovies.domain.usecase.GetWatchedMoviesUseCase
import com.dayker.viewed.watchedmovies.presentation.watchedlist.WatchedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val watchedModule = module {

    single<GetWatchedMoviesUseCase> {
        GetWatchedMoviesUseCase()
    }

    viewModel<WatchedViewModel> {
        WatchedViewModel(getWatchedMoviesUseCase = get())
    }

}