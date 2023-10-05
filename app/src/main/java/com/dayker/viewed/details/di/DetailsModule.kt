package com.dayker.viewed.details.di

import com.dayker.viewed.details.presentation.aboutapp.AboutAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    viewModel<AboutAppViewModel> {
        AboutAppViewModel()
    }
}