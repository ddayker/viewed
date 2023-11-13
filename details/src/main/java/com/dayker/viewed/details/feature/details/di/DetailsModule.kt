package com.dayker.viewed.details.feature.details.di

import com.dayker.viewed.details.feature.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val detailsModule = module {
    viewModelOf(::DetailsViewModel)
}