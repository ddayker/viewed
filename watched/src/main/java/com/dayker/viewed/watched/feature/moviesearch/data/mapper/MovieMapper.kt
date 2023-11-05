package com.dayker.viewed.watched.feature.moviesearch.data.mapper

import com.dayker.viewed.watched.feature.moviesearch.data.datasource.remote.dto.MovieResponse
import com.dayker.viewed.watched.feature.moviesearch.domain.model.MoviePoster

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


