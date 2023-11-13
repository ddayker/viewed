package com.dayker.viewed.details.di

import com.dayker.viewed.authentication.di.authenticationModule
import com.dayker.viewed.details.feature.aboutapp.di.aboutAppModule
import com.dayker.viewed.details.feature.details.di.detailsModule
import org.koin.core.module.Module

object DetailsAuthModules {
    val modules: List<Module> = listOf(
        aboutAppModule,
        detailsModule,
        authenticationModule
    )
}