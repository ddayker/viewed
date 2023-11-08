package com.dayker.viewed.watched.di

import com.dayker.viewed.watched.common.data.di.watchedDataModule
import com.dayker.viewed.watched.common.domain.di.watchedDomainModule
import com.dayker.viewed.watched.common.platform.di.watchedPlatformModule
import com.dayker.viewed.watched.feature.addeditmovie.di.watchedAddEditModule
import com.dayker.viewed.watched.feature.moviereview.di.reviewModel
import com.dayker.viewed.watched.feature.movies.di.watchedMoviesModule
import com.dayker.viewed.watched.feature.moviesearch.di.searchModel
import org.koin.core.module.Module

object WatchedModules {
    val modules: List<Module> = listOf(
        searchModel,
        watchedMoviesModule,
        watchedDomainModule,
        watchedDataModule,
        watchedPlatformModule,
        watchedAddEditModule,
        reviewModel
    )
}