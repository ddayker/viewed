package com.dayker.viewed.watched.feature.moviesearch.di

import com.dayker.viewed.watched.feature.moviesearch.presentation.MovieSearchingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val searchModel = module {
    viewModelOf(::MovieSearchingViewModel)
}