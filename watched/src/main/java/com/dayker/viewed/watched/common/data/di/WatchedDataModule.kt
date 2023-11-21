package com.dayker.viewed.watched.common.data.di


import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import com.dayker.viewed.watched.common.data.datasource.images.FirebaseStorageDataSource
import com.dayker.viewed.watched.common.data.datasource.images.RemoteImageDataSource
import com.dayker.viewed.watched.common.data.datasource.moviesearching.MovieSearchingDataSource
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.NetworkMovieSearchingDataSource
import com.dayker.viewed.watched.common.data.datasource.watchedmovies.RemoteMoviesDataSource
import com.dayker.viewed.watched.common.data.datasource.watchedmovies.remote.FirestoreMoviesDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
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

    singleOf(::FirestoreMoviesDataSource) {
        bind<RemoteMoviesDataSource>()
    }

    singleOf(::FirebaseStorageDataSource) {
        bind<RemoteImageDataSource>()
    }

    single {
        FirebaseFirestore.getInstance()
    }

    single {
        FirebaseStorage.getInstance().reference
    }

    single<ConnectivityManager> {
        androidContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    single {
        Dispatchers.IO
    }


}
