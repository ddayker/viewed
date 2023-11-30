package com.dayker.viewed.watched.common.platform.datasource.movies

import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.platform.datasource.movies.source.MovieDao
import com.dayker.viewed.watched.common.platform.mapper.Movie
import com.dayker.viewed.watched.common.platform.mapper.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DatabaseMoviesDataSource(
    private val dao: MovieDao
) : LocalMoviesDataSource {

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map { entities ->
            entities.map(::Movie)
        }
    }

    override suspend fun getMovieById(id: Long): Movie? {
        return dao.getMovieById(id)?.let(::Movie)
    }

    override suspend fun insertMovie(movie: Movie): Long {
        return dao.insertMovie(MovieEntity(movie))
    }

    override suspend fun deleteMovie(movie: Movie) {
        dao.deleteMovie(MovieEntity(movie))
    }
}