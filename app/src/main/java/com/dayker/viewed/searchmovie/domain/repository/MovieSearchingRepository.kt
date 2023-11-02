package com.dayker.viewed.searchmovie.domain.repository

import com.dayker.viewed.common.Resource
import com.dayker.viewed.searchmovie.domain.model.MoviePoster
import kotlinx.coroutines.flow.Flow

interface MovieSearchingRepository {

    suspend fun getMovies(query: String): Flow<Resource<List<MoviePoster>>>

}