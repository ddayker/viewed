package com.dayker.viewed.watchedmovies.feature.moviesearch.domain.repository

import com.dayker.viewed.core.Resource
import com.dayker.viewed.watchedmovies.feature.moviesearch.domain.model.MoviePoster
import kotlinx.coroutines.flow.Flow

interface MovieSearchingRepository {

    suspend fun getMovies(query: String): Flow<Resource<List<MoviePoster>>>

}