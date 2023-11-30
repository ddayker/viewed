package com.dayker.viewed.watched.feature.moviesearch.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.domain.repository.MovieSearchingRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class MovieSearchingViewModel(
    private val repository: MovieSearchingRepository
) : ViewModel() {

    companion object {
        const val MIN_QUERY_SIZE = 3
    }

    private val _state: MutableState<MovieSearchingState> =
        mutableStateOf(MovieSearchingState.NoRequest(""))
    val state: State<MovieSearchingState> = _state

    private val _actionFlow = MutableSharedFlow<MovieSearchingScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    private var searchJob: Job? = null

    private fun onSearch(query: String) {
        if (query.trim().isEmpty()) {
            _state.value = MovieSearchingState.NoRequest(query = query)
        } else {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                delay(500L)
                if (query.trim().length < MIN_QUERY_SIZE) {
                    _state.value = MovieSearchingState.ToShortQuery(query = query)
                } else {
                    repository.getMovies(query).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                if (result.data.isNullOrEmpty()) {
                                    _state.value = MovieSearchingState.Empty(query = query)
                                } else {
                                    _state.value = MovieSearchingState.Movies(
                                        movies = result.data!!,
                                        successfulQuery = query
                                    )
                                }
                            }

                            is Resource.Error -> {
                                _state.value = MovieSearchingState.Error(query = query)
                            }

                            is Resource.Loading -> {
                                _state.value = MovieSearchingState.Loading(query = query)
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }

    fun onEvent(event: MovieSearchingEvent) {
        when (event) {
            MovieSearchingEvent.BackClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(MovieSearchingScreenAction.GoBack)
                }
            }

            is MovieSearchingEvent.Search -> {
                onSearch(event.query)
            }

            is MovieSearchingEvent.MovieClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(MovieSearchingScreenAction.GoToAdding(event.id))
                }
            }
        }
    }
}