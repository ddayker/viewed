package com.dayker.viewed.watchedmovies.presentation.addeditmovie

sealed class AddEditUiEvent {
    data class SaveMovie(val isPossibleToSave: Boolean) : AddEditUiEvent()
    object DeleteMovie : AddEditUiEvent()
    object ReturnBack : AddEditUiEvent()
}