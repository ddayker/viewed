package com.dayker.viewed.watched.feature.addeditmovie.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.domain.repository.MovieSearchingRepository
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.NEW_MOVIE_ID_KEY
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.SAVED_MOVIE_ID_KEY
import com.dayker.viewed.watched.common.utils.secondsToMinutes
import com.dayker.viewed.watched.common.utils.toDate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class AddEditMovieViewModel(
    private val watchedRepository: WatchedMoviesRepository,
    private val movieSearchingRepository: MovieSearchingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditState())
    val state: State<AddEditState> = _state

    private val _actionFlow = MutableSharedFlow<AddEditMovieScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    private var movieId: Long? = null

    init {
        savedStateHandle.get<Long>(SAVED_MOVIE_ID_KEY)?.let { id ->
            if (id != WatchedNavGraphConstants.NOT_SAVED) {
                _state.value = state.value.copy(isEditing = true)
                viewModelScope.launch {
                    watchedRepository.getMovieById(id)?.also { movie ->
                        movieId = movie.id
                        _state.value = state.value.copy(
                            movie = movie.copy()
                        )
                    }
                }
            }
        }
        savedStateHandle.get<String>(NEW_MOVIE_ID_KEY)?.let { id ->
            if (id != WatchedNavGraphConstants.NOT_NEW) {
                viewModelScope.launch {
                    movieSearchingRepository.getMovieInfo(id).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                if (result.data != null) {
                                    _state.value = state.value.copy(
                                        movie = result.data!!.copy(),
                                        loading = false
                                    )
                                }
                            }

                            is Resource.Error -> {
                                _state.value = state.value.copy(loading = false)
                                _actionFlow.emit(AddEditMovieScreenAction.ShowError)

                            }

                            is Resource.Loading -> {
                                _state.value = state.value.copy(loading = true)
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }

    private fun isPossibleToSave(): Boolean {
        return with(state.value.movie) {
            title.trim()
                .isNotEmpty() && durationMin != 0L && viewingDate != null && releaseDate != null
        }
    }

    fun onEvent(event: AddEditMovieEvent) {
        with(state.value) {
            when (event) {
                is AddEditMovieEvent.ChangeTabPosition -> {
                    _state.value = this.copy(selectedTabIndex = event.tabIndex)
                }

                AddEditMovieEvent.ChangeInputDialogVisibility -> {
                    _state.value = this.copy(showInputDialog = !state.value.showInputDialog)
                }

                is AddEditMovieEvent.ChangeTitle -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(title = event.title)
                    )
                }

                AddEditMovieEvent.ChangeCalendarDialogVisibility -> {
                    _state.value =
                        this.copy(showCalendarDialog = !state.value.showCalendarDialog)
                }

                is AddEditMovieEvent.ChangeDuration -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(durationMin = event.duration.secondsToMinutes())
                    )

                }

                AddEditMovieEvent.ChangeDurationDialogVisibility -> {
                    _state.value = this.copy(showDurationDialog = !state.value.showDurationDialog)
                }

                AddEditMovieEvent.ChangeGenreListDialogVisibility -> {
                    _state.value =
                        this.copy(showGenresListDialog = !state.value.showGenresListDialog)
                }

                is AddEditMovieEvent.ChangeReleaseDate -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(releaseDate = event.releaseDateInMillis.toDate())
                    )

                }

                is AddEditMovieEvent.ChangeReview -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(review = event.review)
                    )
                }

                is AddEditMovieEvent.ChangeViewingDate -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(viewingDate = event.viewingDateInMillis.toDate())
                    )
                }

                is AddEditMovieEvent.ChangeImage -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(imageURL = event.imgURI)
                    )
                }

                is AddEditMovieEvent.ChangeRating -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(rating = event.rating)
                    )
                }

                is AddEditMovieEvent.UpdateGenres -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(genres = event.genres)
                    )
                }

                is AddEditMovieEvent.AddDirector -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(directors = this.movie.directors + event.director)
                    )
                }

                is AddEditMovieEvent.AddStar -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(stars = this.movie.stars + event.star)
                    )
                }

                is AddEditMovieEvent.AddWriter -> {
                    _state.value = this.copy(
                        movie = this.movie.copy(writers = this.movie.writers + event.writer)
                    )
                }

                is AddEditMovieEvent.RemoveDirector -> {
                    val directors = this.movie.directors.toMutableList()
                    directors.removeAt(event.index)
                    _state.value =
                        this.copy(movie = this.movie.copy(directors = directors.toList()))
                }

                is AddEditMovieEvent.RemoveGenre -> {
                    val genres = this.movie.genres.toMutableList()
                    genres.removeAt(event.index)
                    _state.value =
                        this.copy(movie = this.movie.copy(genres = genres.toList()))
                }

                is AddEditMovieEvent.RemoveStar -> {
                    val stars = this.movie.stars.toMutableList()
                    stars.removeAt(event.index)
                    _state.value =
                        this.copy(movie = this.movie.copy(stars = stars.toList()))
                }

                is AddEditMovieEvent.RemoveWriter -> {
                    val writers = this.movie.writers.toMutableList()
                    writers.removeAt(event.index)
                    _state.value =
                        this.copy(movie = this.movie.copy(writers = writers.toList()))
                }

                AddEditMovieEvent.DeleteMovie -> {
                    viewModelScope.launch {
                        watchedRepository.deleteMovie(movie = state.value.movie.copy(id = movieId))
                        _actionFlow.emit(AddEditMovieScreenAction.DeleteMovie)
                    }
                }

                AddEditMovieEvent.SaveMovie -> {
                    viewModelScope.launch {
                        if (isPossibleToSave()) {
                            val movie = state.value.movie.copy(id = movieId)
                            val id = watchedRepository.saveMovie(movie)
                            _actionFlow.emit(
                                AddEditMovieScreenAction.SaveMovie(
                                    isPossibleToSave = true,
                                    id = id
                                )
                            )
                        } else {
                            _actionFlow.emit(AddEditMovieScreenAction.SaveMovie(isPossibleToSave = false))
                        }
                    }
                }

                AddEditMovieEvent.ReturnBack -> {
                    viewModelScope.launch {
                        _actionFlow.emit(AddEditMovieScreenAction.ReturnBack)
                    }
                }

                AddEditMovieEvent.ChangeSavingErrorDialogVisibility -> {
                    _state.value =
                        state.value.copy(showSavingErrorDialog = !state.value.showSavingErrorDialog)
                }

                AddEditMovieEvent.ChangeDeleteConfirmationDialogVisibility -> {
                    _state.value =
                        state.value.copy(showDeleteConfirmationDialog = !state.value.showDeleteConfirmationDialog)
                }
            }
        }
    }
}