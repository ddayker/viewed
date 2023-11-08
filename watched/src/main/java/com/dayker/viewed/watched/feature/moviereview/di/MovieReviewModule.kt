package com.dayker.viewed.watched.feature.moviereview.di

import com.dayker.viewed.watched.feature.moviereview.presentation.MovieReviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val reviewModel = module {
    viewModelOf(::MovieReviewViewModel)
}