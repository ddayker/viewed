package com.dayker.viewed.watched.feature.moviesearch.domain.repository

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.feature.moviesearch.domain.model.MoviePoster
import kotlinx.coroutines.flow.Flow

interface MovieSearchingRepository {

    suspend fun getMovies(query: String): Flow<Resource<List<MoviePoster>>>

}