package com.dayker.viewed.watched.common.data.mapper

import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto.MovieInfo
import com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto.MovieResponse
import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.model.MoviePoster

internal fun MoviePoster(
    response: MovieResponse
): MoviePoster = with(response) {
    MoviePoster(
        id = imdbID,
        poster = poster,
        title = title,
        year = year
    )
}

internal fun Movie(
    response: MovieInfo
): Movie = with(response) {
    Movie(
        title = title,
        releaseDate = released,
        durationMin = runtime.toMinutesOrZero(),
        imageURL = poster,
        genres = genre.split(", ").map { it.trim() },
        stars = actors.split(", ").map { it.trim() },
        writers = writer.split(", ").map { it.trim() },
        directors = director.split(", ").map { it.trim() }
    )
}

fun String.toMinutesOrZero(): Long {
    return try {
        this.replace(Regex("[^0-9]"), "").toLong()
    } catch (e: Exception) {
        0L
    }
}


