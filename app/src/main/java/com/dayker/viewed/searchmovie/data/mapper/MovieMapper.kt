package com.dayker.viewed.searchmovie.data.mapper

import com.dayker.viewed.searchmovie.data.datasource.remote.dto.MovieResponse
import com.dayker.viewed.searchmovie.domain.model.MoviePoster

fun MoviePoster(
    response: MovieResponse
): MoviePoster = with(response) {
    MoviePoster(
        id = imdbID,
        poster = poster,
        title = title,
        year = year
    )
}


