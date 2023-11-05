package com.dayker.viewed.watchedmovies.feature.moviesearch.presentation

sealed class MovieSearchingEvent {
    data class Search(val query: String) : MovieSearchingEvent()
    object BackClicked : MovieSearchingEvent()
}