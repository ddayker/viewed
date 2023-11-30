package com.dayker.viewed.watched.feature.moviereview.presentation

internal sealed class MovieReviewEvent {
    object BackClicked : MovieReviewEvent()
    data class EditClicked(val id: Long) : MovieReviewEvent()
}