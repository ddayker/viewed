package com.dayker.viewed.watchedmovies.feature.addeditmovie.di

import com.dayker.viewed.watchedmovies.feature.addeditmovie.presentation.AddEditMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val watchedAddEditModule = module {
    viewModelOf(::AddEditMovieViewModel)
}