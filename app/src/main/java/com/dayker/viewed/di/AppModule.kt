package com.dayker.viewed.di

import com.dayker.viewed.domain.usecase.GetWatchedMoviesUseCase
import com.dayker.viewed.presentation.details.aboutapp.AboutAppViewModel
import com.dayker.viewed.presentation.watched.WatchedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AboutAppViewModel> {
        AboutAppViewModel()
    }

    single<GetWatchedMoviesUseCase> {
        GetWatchedMoviesUseCase()
    }

    viewModel<WatchedViewModel> {
        WatchedViewModel(getWatchedMoviesUseCase = get())
    }
}