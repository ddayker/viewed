package com.dayker.viewed.watched.feature.addeditmovie.presentation

internal sealed class AddEditMovieScreenAction {
    data class SaveMovie(val isPossibleToSave: Boolean, val id: Long? = null) :
        AddEditMovieScreenAction()

    object DeleteMovie : AddEditMovieScreenAction()
    object ReturnBack : AddEditMovieScreenAction()
    object ShowError : AddEditMovieScreenAction()
}