package com.dayker.viewed.watchedmovies.feature.addeditmovie.presentation

sealed class AddEditMovieScreenAction {
    data class SaveMovie(val isPossibleToSave: Boolean) : AddEditMovieScreenAction()
    object DeleteMovie : AddEditMovieScreenAction()
    object ReturnBack : AddEditMovieScreenAction()
}