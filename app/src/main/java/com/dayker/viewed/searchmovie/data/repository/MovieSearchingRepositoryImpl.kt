package com.dayker.viewed.searchmovie.data.repository

import com.dayker.viewed.common.Resource
import com.dayker.viewed.searchmovie.data.datasource.MovieSearchingDataSource
import com.dayker.viewed.searchmovie.data.mapper.MoviePoster
import com.dayker.viewed.searchmovie.domain.model.MoviePoster
import com.dayker.viewed.searchmovie.domain.repository.MovieSearchingRepository
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
