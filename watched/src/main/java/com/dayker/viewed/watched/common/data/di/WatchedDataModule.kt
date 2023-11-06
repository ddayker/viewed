package com.dayker.viewed.watched.common.data.di


import com.dayker.viewed.watched.common.data.datasource.MovieSearchingDataSource
import com.dayker.viewed.watched.common.data.datasource.remote.NetworkMovieSearchingDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val watchedDataModule = module {

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

}
