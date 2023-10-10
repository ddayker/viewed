package com.dayker.viewed.watchedmovies.presentation.addeditmovie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddEditMovieViewModel() : ViewModel() {

    private val _uiState = mutableStateOf(AddEditUIState())
    val uiState: State<AddEditUIState> = _uiState

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    fun onEvent(event: AddEditMovieEvent) {
        when (event) {
            is AddEditMovieEvent.ChangeTabPosition -> {
                _uiState.value = uiState.value.copy(selectedTabIndex = event.tabIndex)
            }

            AddEditMovieEvent.ChangeInputDialogVisibility -> {
                _uiState.value =
                    uiState.value.copy(showInputDialog = !uiState.value.showInputDialog)
            }

            is AddEditMovieEvent.ChangeTitle -> {
                _movieState.value = movieState.value.copy(title = event.title)
            }

            AddEditMovieEvent.ChangeCalendarDialogVisibility -> {
                _uiState.value =
                    uiState.value.copy(showCalendarDialog = !uiState.value.showCalendarDialog)
            }

            is AddEditMovieEvent.ChangeDuration -> {
                _movieState.value = movieState.value.copy(duration = event.duration)
            }

            AddEditMovieEvent.ChangeDurationDialogVisibility -> {
                _uiState.value =
                    uiState.value.copy(showDurationDialog = !uiState.value.showDurationDialog)
            }

            AddEditMovieEvent.ChangeGenreListDialogVisibility -> {
                _uiState.value =
                    uiState.value.copy(showGenresListDialog = !uiState.value.showGenresListDialog)
            }

            is AddEditMovieEvent.ChangeReleaseDate -> {
                _movieState.value = movieState.value.copy(releaseDate = event.releaseDateInMillis)
            }

            is AddEditMovieEvent.ChangeReview -> {
                _movieState.value = movieState.value.copy(review = event.review)
            }

            is AddEditMovieEvent.ChangeViewingDate -> {
                _movieState.value = movieState.value.copy(viewingDate = event.viewingDateInMillis)
            }

            is AddEditMovieEvent.ChangeRating -> {
                _movieState.value = movieState.value.copy(rating = event.rating)
            }

            is AddEditMovieEvent.UpdateGenres -> {
                _movieState.value = movieState.value.copy(genres = event.genres)
            }

            is AddEditMovieEvent.AddDirector -> {
                _movieState.value =
                    movieState.value.copy(directors = movieState.value.directors + event.director)
            }

            is AddEditMovieEvent.AddStar -> {
                _movieState.value =
                    movieState.value.copy(stars = movieState.value.stars + event.star)
            }

            is AddEditMovieEvent.AddWriter -> {
                _movieState.value =
                    movieState.value.copy(writers = movieState.value.writers + event.writer)
            }

            is AddEditMovieEvent.RemoveDirector -> {
                val directors = movieState.value.directors.toMutableList()
                directors.removeAt(event.index)
                _movieState.value = movieState.value.copy(directors = directors.toList())
            }

            is AddEditMovieEvent.RemoveGenre -> {
                val genres = movieState.value.genres.toMutableList()
                genres.removeAt(event.index)
                _movieState.value = movieState.value.copy(genres = genres.toList())
            }

            is AddEditMovieEvent.RemoveStar -> {
                val stars = movieState.value.stars.toMutableList()
                stars.removeAt(event.index)
                _movieState.value = movieState.value.copy(stars = stars.toList())
            }

            is AddEditMovieEvent.RemoveWriter -> {
                val writers = movieState.value.writers.toMutableList()
                writers.removeAt(event.index)
                _movieState.value = movieState.value.copy(writers = writers.toList())
            }

            is AddEditMovieEvent.ChangeImageURL -> {
                _movieState.value = movieState.value.copy(imageURL = event.imageUrl)
            }
        }
    }
}