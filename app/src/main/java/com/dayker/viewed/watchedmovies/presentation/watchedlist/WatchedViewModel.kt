package com.dayker.viewed.watchedmovies.presentation.watchedlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.watchedmovies.domain.usecase.GetWatchedMoviesUseCase
import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder
import com.dayker.viewed.watchedmovies.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WatchedViewModel(
    private val getWatchedMoviesUseCase: GetWatchedMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WatchedState())
    val state: State<WatchedState> = _state

    private var getMoviesJob: Job? = null

    init {
        getMovies(MoviesOrder.Rating(OrderType.Descending))
    }

    private fun getMovies(order: MoviesOrder) {
        getMoviesJob?.cancel()
        getMoviesJob = getWatchedMoviesUseCase(order).onEach { movies ->
            _state.value = state.value.copy(movies = movies, moviesOrder = order)
        }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: WatchedScreenEvent) {
        when (event) {
            is WatchedScreenEvent.Order -> {
                if (state.value.moviesOrder::class == event.moviesOrder::class &&
                    state.value.moviesOrder.orderType == event.moviesOrder.orderType
                ) {
                    return
                }
                getMovies(event.moviesOrder)
            }

            WatchedScreenEvent.HideFab -> {
                _state.value = state.value.copy(
                    isFABVisible = false
                )
            }

            WatchedScreenEvent.ShowFAB -> {
                _state.value = state.value.copy(
                    isFABVisible = true
                )
            }

            WatchedScreenEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSelectionVisible = !state.value.isOrderSelectionVisible
                )
            }
        }
    }
}