package com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote

import com.dayker.viewed.watched.BuildConfig


object HttpRoutes {

    const val BASE_URL = "www.omdbapi.com"

    const val API_KEY_PARAM = "apikey"

    const val SEARCH_PARAM = "s"

    const val API_KEY = BuildConfig.API_KEY
}