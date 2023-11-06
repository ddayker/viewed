package com.dayker.viewed.watched.common.data.datasource

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.data.datasource.remote.dto.MovieInfo
import com.dayker.viewed.watched.common.data.datasource.remote.dto.MovieResponse

interface MovieSearchingDataSource {
    suspend fun getMovies(query: String): Resource<List<MovieResponse>>
    suspend fun getMovieInfo(id: String): Resource<MovieInfo>
}