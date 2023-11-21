package com.dayker.viewed.watched.feature.movies.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.core.util.Resource
import com.dayker.viewed.watched.common.domain.repository.WatchedMoviesRepository
import com.dayker.viewed.watched.common.domain.usecase.GetWatchedMoviesUseCase
import com.dayker.viewed.watched.common.domain.util.MoviesOrder
import com.dayker.viewed.watched.common.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class WatchedMoviesViewModel(
    private val getWatchedMoviesUseCase: GetWatchedMoviesUseCase,
    private val repository: WatchedMoviesRepository
) : ViewModel() {

    private val _state = mutableStateOf(WatchedMoviesState())
    val state: State<WatchedMoviesState> = _state

    private val _actionFlow = MutableSharedFlow<WatchedMoviesScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    private var getMoviesJob: Job? = null

    init {
        viewModelScope.launch {
            if (repository.isUnsavedLocalData()) {
                _state.value = state.value.copy(isUnsavedLocalData = true)
            }
        }

        getMovies(MoviesOrder.Rating(OrderType.Descending))
    }

    private fun getMovies(order: MoviesOrder) {
        getMoviesJob?.cancel()
        getMoviesJob = getWatchedMoviesUseCase(order).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data != null) {
                        if (result.data!!.isNotEmpty()) {
                            _state.value = state.value.copy(
                                movies = result.data!!,
                                moviesOrder = order,
                                isEmpty = false,
                                isLoading = false
                            )
                        } else {
                            _state.value = state.value.copy(
                                movies = result.data!!,
                                moviesOrder = order,
                                isEmpty = true,
                                isLoading = false
                            )
                        }
                    }
                }

                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true, isEmpty = false)
                }

                is Resource.Error -> {
                    println(result.message)
                }
            }
        }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: WatchedMoviesScreenEvent) {
        when (event) {
            is WatchedMoviesScreenEvent.Order -> {
                if (state.value.moviesOrder::class == event.moviesOrder::class &&
                    state.value.moviesOrder.orderType == event.moviesOrder.orderType
                ) {
                    return
                }
                getMovies(event.moviesOrder)
            }

            WatchedMoviesScreenEvent.HideFAB -> {
                _state.value = state.value.copy(
                    isFABVisible = false,
                    isFABExtended = false
                )
            }

            WatchedMoviesScreenEvent.ShowFAB -> {
                _state.value = state.value.copy(
                    isFABVisible = true
                )
            }

            WatchedMoviesScreenEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }

            WatchedMoviesScreenEvent.ExtendFAB -> {
                _state.value = state.value.copy(
                    isFABExtended = !state.value.isFABExtended
                )
            }

            WatchedMoviesScreenEvent.AddBySearchClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(WatchedMoviesScreenAction.OpenSearch)
                }
            }

            WatchedMoviesScreenEvent.AddManuallyClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(WatchedMoviesScreenAction.OpenManuallyAdding)
                }
            }

            is WatchedMoviesScreenEvent.MovieClicked -> {
                viewModelScope.launch {
                    _actionFlow.emit(WatchedMoviesScreenAction.OpenMovieInfo(id = event.id))
                }
            }

            WatchedMoviesScreenEvent.AddToAccountClicked -> {
                _state.value = state.value.copy(isUnsavedLocalData = false)
                viewModelScope.launch() {
                    repository.transferDataFromLocalToRemote()
                }
            }

            WatchedMoviesScreenEvent.DeleteLocalClicked -> {
                _state.value = state.value.copy(isUnsavedLocalData = false)
                viewModelScope.launch() {
                    repository.clearLocalData()
                }
            }
        }
    }
}