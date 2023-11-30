package com.dayker.viewed.watched.feature.moviesearch.presentation

internal sealed class MovieSearchingScreenAction {
    object GoBack : MovieSearchingScreenAction()
    data class GoToAdding(val id: String) : MovieSearchingScreenAction()
}