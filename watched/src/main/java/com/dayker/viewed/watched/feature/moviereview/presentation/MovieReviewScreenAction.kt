package com.dayker.viewed.watched.feature.moviereview.presentation

internal sealed class MovieReviewScreenAction {
    object GoBack : MovieReviewScreenAction()
    data class GoToEditing(val id: Long) : MovieReviewScreenAction()
}