package com.dayker.viewed.watched.feature.addeditmovie.di

import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val watchedAddEditModule = module {
    viewModelOf(::AddEditMovieViewModel)
}