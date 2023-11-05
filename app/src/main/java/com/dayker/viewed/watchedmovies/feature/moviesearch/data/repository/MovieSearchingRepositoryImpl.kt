package com.dayker.viewed.watchedmovies.feature.moviesearch.data.repository

import com.dayker.viewed.core.Resource
import com.dayker.viewed.watchedmovies.feature.moviesearch.data.datasource.MovieSearchingDataSource
import com.dayker.viewed.watchedmovies.feature.moviesearch.data.mapper.MoviePoster
import com.dayker.viewed.watchedmovies.feature.moviesearch.domain.model.MoviePoster
import com.dayker.viewed.watchedmovies.feature.moviesearch.domain.repository.MovieSearchingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieSearchingRepositoryImpl(
    private val dataSource: MovieSearchingDataSource
) : MovieSearchingRepository {
    override suspend fun getMovies(query: String): Flow<Resource<List<MoviePoster>>> = flow {
        emit(Resource.Loading())
        when (val response = dataSource.getMovies(query)) {
            is Resource.Error -> {
                emit(Resource.Error(response.message))
            }

            is Resource.Success -> {
                if (response.data != null)
                    emit(Resource.Success(data = response.data.map(::MoviePoster)))
            }

            else -> {}
        }
    }
}
