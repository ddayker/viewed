package com.dayker.viewed.watched.common.platform.mapper

import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.platform.mapper.EntityMapperUtil.DEFAULT_SEPARATOR
import com.dayker.viewed.watched.common.platform.model.MovieEntity

internal fun Movie(
    e: MovieEntity
) = Movie(
    id = e.id,
    title = e.title,
    releaseDate = e.releaseDate,
    durationMin = e.duration,
    rating = e.rating,
    review = e.review,
    viewingDate = e.viewingDate,
    imageURL = e.imageURL,
    genres = e.genres.toList(),
    directors = e.directors.toList(),
    writers = e.writers.toList(),
    stars = e.stars.toList()
)

internal fun MovieEntity(
    m: Movie
) = MovieEntity(
    id = m.id,
    title = m.title,
    releaseDate = m.releaseDate,
    duration = m.durationMin,
    rating = m.rating,
    review = m.review,
    viewingDate = m.viewingDate,
    imageURL = m.imageURL,
    genres = m.genres.toSeparatedString(),
    directors = m.directors.toSeparatedString(),
    writers = m.writers.toSeparatedString(),
    stars = m.stars.toSeparatedString()
)


object EntityMapperUtil {
    const val DEFAULT_SEPARATOR = "|"
}

fun List<String>.toSeparatedString(separator: String = DEFAULT_SEPARATOR): String {
    return this.joinToString(separator)
}

fun String.toList(separator: String = DEFAULT_SEPARATOR): List<String> {
    return if (this.isNotEmpty()) this.split(separator)
    else listOf()
}