package com.dayker.viewed.details.feature.aboutapp.di

import com.dayker.viewed.details.feature.aboutapp.presentation.AboutAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aboutAppModule = module {

    viewModel<AboutAppViewModel> {
        AboutAppViewModel()
    }
}