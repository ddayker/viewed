package com.dayker.viewed.watched.feature.moviesearch.di

import com.dayker.viewed.watched.feature.moviesearch.data.datasource.MovieSearchingDataSource
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.NetworkMovieSearchingDataSource
import com.dayker.viewed.watched.feature.moviesearch.data.repository.MovieSearchingRepositoryImpl
import com.dayker.viewed.watched.feature.moviesearch.domain.repository.MovieSearchingRepository
import com.dayker.viewed.watched.feature.moviesearch.presentation.MovieSearchingViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val searchModel = module {

    single<HttpClient> {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json()
            }
        }
    }

    singleOf(::NetworkMovieSearchingDataSource) {
        bind<MovieSearchingDataSource>()
    }

    singleOf(::MovieSearchingRepositoryImpl) {
        bind<MovieSearchingRepository>()
    }

    viewModelOf(::MovieSearchingViewModel)
}