package com.dayker.viewed.watchedmovies.feature.moviesearch.data.datasource

import com.dayker.viewed.core.Resource
import com.dayker.viewed.watchedmovies.feature.moviesearch.data.datasource.remote.dto.MovieResponse

interface MovieSearchingDataSource {
    suspend fun getMovies(query: String): Resource<List<MovieResponse>>
}