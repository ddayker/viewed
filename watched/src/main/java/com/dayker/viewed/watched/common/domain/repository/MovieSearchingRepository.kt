package com.dayker.viewed.watched.common.domain.repository

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.model.MoviePoster
import kotlinx.coroutines.flow.Flow

internal interface MovieSearchingRepository {

    suspend fun getMovies(query: String): Flow<Resource<List<MoviePoster>>>

    suspend fun getMovieInfo(id: String): Flow<Resource<Movie>>

}