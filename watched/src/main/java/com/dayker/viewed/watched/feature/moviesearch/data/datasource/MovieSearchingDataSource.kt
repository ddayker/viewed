package com.dayker.viewed.watched.feature.moviesearch.data.datasource

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.dto.MovieResponse

interface MovieSearchingDataSource {
    suspend fun getMovies(query: String): Resource<List<MovieResponse>>
}