package com.dayker.viewed.di

import com.dayker.viewed.presentation.details.aboutapp.AboutAppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AboutAppViewModel> {
        AboutAppViewModel()
    }
}