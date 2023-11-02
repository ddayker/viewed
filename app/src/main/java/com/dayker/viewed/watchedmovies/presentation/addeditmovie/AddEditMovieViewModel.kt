package com.dayker.viewed.watchedmovies.presentation.addeditmovie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.watchedmovies.data.repository.utils.CacheUtils.getIdImageFileName
import com.dayker.viewed.watchedmovies.domain.repository.ImageCachingRepository
import com.dayker.viewed.watchedmovies.domain.usecase.AddWatchedMovieUseCase
import com.dayker.viewed.watchedmovies.domain.usecase.DeleteWatchedMovieUseCase
import com.dayker.viewed.watchedmovies.domain.usecase.GetWatchedMovieUseCase
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.mapper.toMovie
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.mapper.toMovieState
import com.dayker.viewed.watchedmovies.presentation.navigation.WatchedNavGraphConstants
import com.dayker.viewed.watchedmovies.presentation.navigation.WatchedNavGraphConstants.MOVIE_ID_KEY
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddEditMovieViewModel constructor(
    private val addMovie: AddWatchedMovieUseCase,
    private val deleteMovie: DeleteWatchedMovieUseCase,
    private val getMovie: GetWatchedMovieUseCase,
    private val imageCachingRepository: ImageCachingRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = mutableStateOf(AddEditUIState())
    val uiState: State<AddEditUIState> = _uiState

    private val _movieState = mutableStateOf(MovieState())
    val movieState: State<MovieState> = _movieState

    private val _eventFlow = MutableSharedFlow<AddEditUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var movieId: Long? = null

    init {
        savedStateHandle.get<Long>(MOVIE_ID_KEY)?.let { id ->
            if (id != WatchedNavGraphConstants.EMPTY_ID) {
                _uiState.value = uiState.value.copy(isEditing = true)
                viewModelScope.launch {
                    getMovie(id)?.also { movie ->
                        movieId = movie.id
                        _movieState.value = movie.toMovieState()
                        println(movie.toMovieState())
                    }
                }
            }
        }
    }

    private fun isPossibleToSave(): Boolean {
        return with(movieState.value) {
            title.trim().isNotEmpty() && duration != 0L && viewingDate != null
        }
    }

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
                _movieState.value =
                    movieState.value.copy(releaseDate = event.releaseDateInMillis)
            }

            is AddEditMovieEvent.ChangeReview -> {
                _movieState.value = movieState.value.copy(review = event.review)
            }

            is AddEditMovieEvent.ChangeViewingDate -> {
                _movieState.value =
                    movieState.value.copy(viewingDate = event.viewingDateInMillis)
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


            is AddEditMovieEvent.SaveUploadedImage -> {
                val isImageAlreadyChanged = movieState.value.isImageChanged
                if (isImageAlreadyChanged) {
                    val previousImageName =
                        movieState.value.imageURL?.substringAfterLast("/").toString()
                    imageCachingRepository.deleteImage(previousImageName)
                }
                val imageCacheUri = imageCachingRepository.cacheImage(filePath = event.filePath)
                _movieState.value =
                    movieState.value.copy(imageURL = imageCacheUri, isImageChanged = true)
            }

            AddEditMovieEvent.DeleteMovie -> {
                viewModelScope.launch {
                    deleteMovie(movie = movieState.value.toMovie(id = movieId))
                    val isImageChanged = movieState.value.isImageChanged
                    if (isImageChanged) {
                        val unsavedImageName =
                            movieState.value.imageURL?.substringAfterLast("/").toString()
                        imageCachingRepository.deleteImage(unsavedImageName)
                    }
                    val fileName = getIdImageFileName(movieId)
                    imageCachingRepository.deleteImage(fileName)
                    _eventFlow.emit(AddEditUiEvent.DeleteMovie)
                }
            }

            AddEditMovieEvent.SaveMovie -> {
                viewModelScope.launch {
                    if (isPossibleToSave()) {
                        if (movieId == null) {
                            val newMovie = movieState.value.toMovie(id = movieId)
                            movieId = addMovie(newMovie)
                        }
                        val fileName = getIdImageFileName(movieId)
                        val movieImageCacheFileUri =
                            imageCachingRepository.changeSessionUriCacheNameToPermanent(
                                sessionImageUri = movieState.value.imageURL.toString(),
                                permanentFileName = fileName
                            )
                        _movieState.value = movieState.value.copy(imageURL = movieImageCacheFileUri)
                        val movie = movieState.value.toMovie(id = movieId)
                        addMovie(movie)
                        _eventFlow.emit(AddEditUiEvent.SaveMovie(isPossibleToSave = true))
                    } else {
                        _eventFlow.emit(AddEditUiEvent.SaveMovie(isPossibleToSave = false))
                    }
                }
            }

            AddEditMovieEvent.ReturnBack -> {
                viewModelScope.launch {
                    val isImageChanged = movieState.value.isImageChanged
                    if (isImageChanged) {
                        val unsavedImageName =
                            movieState.value.imageURL?.substringAfterLast("/").toString()
                        imageCachingRepository.deleteImage(unsavedImageName)
                    }
                    _eventFlow.emit(AddEditUiEvent.ReturnBack)
                }
            }

            AddEditMovieEvent.ChangeSavingErrorDialogVisibility -> {
                _uiState.value =
                    uiState.value.copy(showSavingErrorDialog = !uiState.value.showSavingErrorDialog)
            }

            AddEditMovieEvent.ChangeDeleteConfirmationDialogVisibility -> {
                _uiState.value =
                    uiState.value.copy(showDeleteConfirmationDialog = !uiState.value.showDeleteConfirmationDialog)
            }
        }
    }
}