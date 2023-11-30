package com.dayker.viewed.watched.feature.moviereview.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class MovieReviewViewModel(
    private val watchedRepository: WatchedMoviesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<MovieReviewState> =
        mutableStateOf(MovieReviewState())
    val state: State<MovieReviewState> = _state

    private val _actionFlow = MutableSharedFlow<MovieReviewScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>(WatchedNavGraphConstants.SAVED_MOVIE_ID_KEY)?.let { id ->
            if (id != WatchedNavGraphConstants.NOT_SAVED) {
                viewModelScope.launch {
                    watchedRepository.getMovieById(id)?.also { movie ->
                        _state.value = state.value.copy(
                            movie = movie.copy(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: MovieReviewEvent) {
        when (event) {
            MovieReviewEvent.BackClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(MovieReviewScreenAction.GoBack)
                }
            }

            is MovieReviewEvent.EditClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(MovieReviewScreenAction.GoToEditing(id = event.id))
                }
            }
        }
    }

}