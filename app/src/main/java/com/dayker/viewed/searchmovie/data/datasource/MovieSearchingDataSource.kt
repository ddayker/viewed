package com.dayker.viewed.searchmovie.data.datasource

import com.dayker.viewed.common.Resource
import com.dayker.viewed.searchmovie.data.datasource.remote.dto.MovieResponse

interface MovieSearchingDataSource {
    suspend fun getMovies(query: String): Resource<List<MovieResponse>>
}