package com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.AddEditMovieEvent
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.AddEditMovieViewModel
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.dialogs.InputTextDialog
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.dialogs.ListDialog
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.FlowRowChips
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.ValueTitle
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsTab(
    modifier: Modifier = Modifier,
    viewModel: AddEditMovieViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.value
    val movieState = viewModel.movieState.value
    var inputDialogAction by remember {
        mutableStateOf<InputAction>(InputAction.UpdateDirectors)
    }

    ListDialog(
        title = stringResource(R.string.pick_genres),
        visible = uiState.showGenresListDialog,
        onPositiveClick = { genres ->
            viewModel.onEvent(AddEditMovieEvent.UpdateGenres(genres))
        },
        pickedGenres = movieState.genres,
        onCloseRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeGenreListDialogVisibility)
        }
    )

    InputTextDialog(
        visible = uiState.showInputDialog,
        title = stringResource(R.string.write_the_name),
        text = "",
        onPositiveClick = { name ->
            when (inputDialogAction) {
                InputAction.UpdateDirectors -> {
                    viewModel.onEvent(AddEditMovieEvent.AddDirector(name))
                }

                InputAction.UpdateStars -> {
                    viewModel.onEvent(AddEditMovieEvent.AddStar(name))
                }

                InputAction.UpdateWriters -> {
                    viewModel.onEvent(AddEditMovieEvent.AddWriter(name))
                }
            }
        },
        onCloseRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
        }
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column() {
            ValueTitle(
                text = stringResource(R.string.genre_s),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_list_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                },
                onClick = {
                    viewModel.onEvent(AddEditMovieEvent.ChangeGenreListDialogVisibility)
                }
            )
            movieState.genres.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            viewModel.onEvent(AddEditMovieEvent.RemoveGenre(index))
                        }
                    )
                } else {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            ValueTitle(
                text = stringResource(R.string.director_s),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_add_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    inputDialogAction = InputAction.UpdateDirectors
                    viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            movieState.directors.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            viewModel.onEvent(AddEditMovieEvent.RemoveDirector(index))
                        }
                    )
                } else {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            ValueTitle(
                text = stringResource(R.string.writer_s),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_add_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    inputDialogAction = InputAction.UpdateWriters
                    viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            movieState.writers.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            viewModel.onEvent(AddEditMovieEvent.RemoveWriter(index))
                        }
                    )
                } else {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            ValueTitle(
                text = stringResource(R.string.stars),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.round_add_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    inputDialogAction = InputAction.UpdateStars
                    viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            movieState.stars.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            viewModel.onEvent(AddEditMovieEvent.RemoveStar(index))
                        }
                    )
                } else {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

sealed class InputAction {
    object UpdateDirectors : InputAction()
    object UpdateWriters : InputAction()
    object UpdateStars : InputAction()
}

