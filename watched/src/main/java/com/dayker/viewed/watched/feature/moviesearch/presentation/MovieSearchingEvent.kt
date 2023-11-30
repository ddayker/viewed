package com.dayker.viewed.watched.feature.moviesearch.presentation

internal sealed class MovieSearchingEvent {
    data class Search(val query: String) : MovieSearchingEvent()
    object BackClicked : MovieSearchingEvent()
    data class MovieClicked(val id: String) : MovieSearchingEvent()
}