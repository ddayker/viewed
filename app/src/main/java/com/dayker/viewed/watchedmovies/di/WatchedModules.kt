package com.dayker.viewed.watchedmovies.di

import com.dayker.viewed.watchedmovies.common.domain.di.watchedDomainModule
import com.dayker.viewed.watchedmovies.common.platform.di.watchedPlatformModule
import com.dayker.viewed.watchedmovies.feature.addeditmovie.di.watchedAddEditModule
import com.dayker.viewed.watchedmovies.feature.movies.di.watchedMoviesModule
import com.dayker.viewed.watchedmovies.feature.moviesearch.di.searchModel
import org.koin.core.module.Module

object WatchedModules {
    val modules: List<Module> = listOf(
        searchModel,
        watchedMoviesModule,
        watchedDomainModule,
        watchedPlatformModule,
        watchedAddEditModule
    )
}