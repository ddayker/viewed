package com.dayker.viewed.watchedmovies.presentation.addeditmovie

sealed class AddEditUiEvent {
    object SaveMovie : AddEditUiEvent()
    object DeleteMovie : AddEditUiEvent()
    object ReturnBack : AddEditUiEvent()
}