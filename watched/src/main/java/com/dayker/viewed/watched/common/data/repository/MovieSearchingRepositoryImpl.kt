package com.dayker.viewed.watched.common.data.repository

import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.data.datasource.moviesearching.MovieSearchingDataSource
import com.dayker.viewed.watched.common.data.mapper.Movie
import com.dayker.viewed.watched.common.data.mapper.MoviePoster
import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.model.MoviePoster
import com.dayker.viewed.watched.common.domain.repository.MovieSearchingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class MovieSearchingRepositoryImpl(
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
                    emit(Resource.Success(data = response.data!!.map(::MoviePoster)))
            }

            else -> {}
        }
    }

    override suspend fun getMovieInfo(id: String): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())
        when (val response = dataSource.getMovieInfo(id)) {
            is Resource.Error -> {
                emit(Resource.Error(response.message))
            }

            is Resource.Success -> {
                if (response.data != null)
                    emit(Resource.Success(data = Movie(response.data!!)))
            }

            else -> {}
        }
    }
}

