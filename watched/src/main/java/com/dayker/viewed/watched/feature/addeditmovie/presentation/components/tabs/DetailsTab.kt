package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs

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
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieEvent
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditState
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.InputTextDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.ListDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.FlowRowChips
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.ValueTitle

@Composable
internal fun DetailsTab(
    modifier: Modifier = Modifier,
    state: AddEditState,
    onEvent: (AddEditMovieEvent) -> Unit
) {
    var inputDialogAction by remember {
        mutableStateOf<InputAction>(InputAction.UpdateDirectors)
    }

    ListDialog(
        title = stringResource(R.string.pick_genres),
        visible = state.showGenresListDialog,
        onPositiveClick = { genres ->
            onEvent(AddEditMovieEvent.UpdateGenres(genres))
        },
        pickedGenres = state.movie.genres,
        onCloseRequest = {
            onEvent(AddEditMovieEvent.ChangeGenreListDialogVisibility)
        }
    )

    InputTextDialog(
        visible = state.showInputDialog,
        title = stringResource(R.string.write_the_name),
        text = "",
        onPositiveClick = { name ->
            when (inputDialogAction) {
                InputAction.UpdateDirectors -> {
                    onEvent(AddEditMovieEvent.AddDirector(name))
                }

                InputAction.UpdateStars -> {
                    onEvent(AddEditMovieEvent.AddStar(name))
                }

                InputAction.UpdateWriters -> {
                    onEvent(AddEditMovieEvent.AddWriter(name))
                }
            }
        },
        onCloseRequest = {
            onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
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
                    onEvent(AddEditMovieEvent.ChangeGenreListDialogVisibility)
                }
            )
            state.movie.genres.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            onEvent(AddEditMovieEvent.RemoveGenre(index))
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
                    onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            state.movie.directors.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            onEvent(AddEditMovieEvent.RemoveDirector(index))
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
                    onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            state.movie.writers.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            onEvent(AddEditMovieEvent.RemoveWriter(index))
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
                    onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            state.movie.stars.let {
                if (it.isNotEmpty()) {
                    FlowRowChips(
                        labels = it,
                        onClick = { index ->
                            onEvent(AddEditMovieEvent.RemoveStar(index))
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

