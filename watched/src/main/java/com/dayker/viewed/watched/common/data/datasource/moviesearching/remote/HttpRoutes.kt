package com.dayker.viewed.watched.common.data.datasource.moviesearching.remote

import com.dayker.viewed.watched.BuildConfig

object HttpRoutes {

    const val BASE_URL = "www.omdbapi.com"

    const val API_KEY_PARAM = "apikey"

    const val SEARCH_PARAM = "s"

    const val ID_PARAM = "i"

    const val TYPE_PARAM = "type"

    const val TYPE_MOVIE = "movie"

    const val API_KEY = BuildConfig.API_KEY
}