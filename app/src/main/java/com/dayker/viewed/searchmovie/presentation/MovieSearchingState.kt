package com.dayker.viewed.searchmovie.presentation

import com.dayker.viewed.searchmovie.domain.model.MoviePoster

sealed class MovieSearchingState(val query: String = "") {

    class NoRequest(query: String) : MovieSearchingState(query)
    class Loading(query: String) : MovieSearchingState(query)
    class Empty(query: String) : MovieSearchingState(query)
    class Error(query: String) : MovieSearchingState(query)
    class ToShortQuery(query: String) : MovieSearchingState(query)
    data class Movies(val movies: List<MoviePoster>, val successfulQuery: String) :
        MovieSearchingState(query = successfulQuery)

}


