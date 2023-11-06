package com.dayker.viewed.watched.feature.moviesearch.presentation

sealed class MovieSearchingEvent {
    data class Search(val query: String) : MovieSearchingEvent()
    object BackClicked : MovieSearchingEvent()
    data class MovieClicked(val id: String) : MovieSearchingEvent()
}