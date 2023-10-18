package com.dayker.viewed.watchedmovies.presentation.addeditmovie.mapper

import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.MovieState
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.mapper.MapperUtil.DEFAULT_SEPARATOR

fun Movie.toMovieState() = MovieState(
    title = this.title,
    releaseDate = this.releaseDate,
    duration = this.duration,
    rating = this.rating,
    review = this.review,
    viewingDate = this.viewingDate,
    imageURL = this.imageURL,
    genres = this.genres.toList(),
    directors = this.directors.toList(),
    writers = this.writers.toList(),
    stars = this.stars.toList()
)

fun MovieState.toMovie(id: Long? = null) = Movie(
    id = id,
    title = this.title,
    releaseDate = this.releaseDate,
    duration = this.duration,
    rating = this.rating,
    review = this.review,
    viewingDate = this.viewingDate,
    imageURL = this.imageURL ?: "",
    genres = this.genres.toValueString(),
    directors = this.directors.toValueString(),
    writers = this.writers.toValueString(),
    stars = this.stars.toValueString()
)

object MapperUtil {
    const val DEFAULT_SEPARATOR = "|"
}

fun List<String>.toValueString(separator: String = DEFAULT_SEPARATOR): String {
    return this.joinToString(separator)
}

fun String.toList(separator: String = DEFAULT_SEPARATOR): List<String> {
    return if (this.isNotEmpty()) this.split(separator)
    else listOf()
}