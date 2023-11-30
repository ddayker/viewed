package com.dayker.viewed.watched.feature.moviesearch.presentation

import com.dayker.viewed.watched.common.domain.model.MoviePoster

internal sealed class MovieSearchingState(val query: String = "") {

    class NoRequest(query: String) : MovieSearchingState(query)
    class Loading(query: String) : MovieSearchingState(query)
    class Empty(query: String) : MovieSearchingState(query)
    class Error(query: String) : MovieSearchingState(query)
    class ToShortQuery(query: String) : MovieSearchingState(query)
    data class Movies(val movies: List<MoviePoster>, val successfulQuery: String) :
        MovieSearchingState(query = successfulQuery)

}


