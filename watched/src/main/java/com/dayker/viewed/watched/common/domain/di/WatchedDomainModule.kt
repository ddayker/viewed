package com.dayker.viewed.watched.common.domain.di


import com.dayker.viewed.watched.common.data.repository.WatchedMoviesRepositoryImpl
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watched.common.domain.usecase.AddWatchedMovieUseCase
import com.dayker.viewed.watched.common.domain.usecase.DeleteWatchedMovieUseCase
import com.dayker.viewed.watched.common.domain.usecase.GetWatchedMovieUseCase
import com.dayker.viewed.watched.common.domain.usecase.GetWatchedMoviesUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val watchedDomainModule = module {

    singleOf(::WatchedMoviesRepositoryImpl) {
        bind<WatchedMoviesRepository>()
    }

    singleOf(::GetWatchedMoviesUseCase)

    singleOf(::GetWatchedMovieUseCase)

    singleOf(::AddWatchedMovieUseCase)

    singleOf(::DeleteWatchedMovieUseCase)

}
