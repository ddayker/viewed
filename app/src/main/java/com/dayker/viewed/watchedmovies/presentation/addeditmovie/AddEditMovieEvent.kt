package com.dayker.viewed.watchedmovies.presentation.addeditmovie

sealed class AddEditMovieEvent {
    data class ChangeTabPosition(val tabIndex: Int) : AddEditMovieEvent()
    object ChangeInputDialogVisibility : AddEditMovieEvent()
    data class ChangeTitle(val title: String) : AddEditMovieEvent()
    data class ChangeReview(val review: String) : AddEditMovieEvent()
    object ChangeCalendarDialogVisibility : AddEditMovieEvent()
    data class ChangeReleaseDate(val releaseDateInMillis: Long) : AddEditMovieEvent()
    data class ChangeViewingDate(val viewingDateInMillis: Long) : AddEditMovieEvent()
    object ChangeDurationDialogVisibility : AddEditMovieEvent()
    data class ChangeDuration(val duration: Long) : AddEditMovieEvent()
    object ChangeGenreListDialogVisibility : AddEditMovieEvent()
    data class ChangeRating(val rating: Float) : AddEditMovieEvent()
    data class UpdateGenres(val genres: List<String>) : AddEditMovieEvent()
    data class AddDirector(val director: String) : AddEditMovieEvent()
    data class AddWriter(val writer: String) : AddEditMovieEvent()
    data class AddStar(val star: String) : AddEditMovieEvent()
    data class RemoveGenre(val index: Int) : AddEditMovieEvent()
    data class RemoveDirector(val index: Int) : AddEditMovieEvent()
    data class RemoveWriter(val index: Int) : AddEditMovieEvent()
    data class RemoveStar(val index: Int) : AddEditMovieEvent()
    data class ChangeImageURL(val imageUrl: String) : AddEditMovieEvent()
}