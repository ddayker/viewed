package com.dayker.viewed.details.di

import com.dayker.viewed.details.feature.aboutapp.di.aboutAppModule
import org.koin.core.module.Module

object DetailsModules {
    val modules: List<Module> = listOf(
        aboutAppModule
    )
}