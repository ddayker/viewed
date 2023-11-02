package com.dayker.viewed.searchmovie.presentation

sealed class MovieSearchingEvent {
    data class Search(val query: String) : MovieSearchingEvent()
    object BackClicked : MovieSearchingEvent()
}