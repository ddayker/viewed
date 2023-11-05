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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.common.utils.minutesToSeconds
import com.dayker.viewed.watched.common.utils.toLongDate
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieEvent
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieViewModel
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.CalendarPickerDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.InputTextDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.TimeDurationDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.ReadOnlyTextField
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.ValueTitle
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieTab(
    modifier: Modifier = Modifier,
    viewModel: AddEditMovieViewModel = getViewModel()
) {
    val state = viewModel.state.value
    InputTextDialog(
        visible = state.showInputDialog,
        title = stringResource(R.string.movie_title),
        text = state.movie.title,
        onPositiveClick = { title ->
            viewModel.onEvent(AddEditMovieEvent.ChangeTitle(title))
        },
        onCloseRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
        },
    )

    CalendarPickerDialog(
        visible = state.showCalendarDialog,
        pickedDate = state.movie.releaseDate?.toLongDate(),
        onConfirm = { dateInMillis ->
            viewModel.onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
            viewModel.onEvent(AddEditMovieEvent.ChangeReleaseDate(dateInMillis))
        },
        onDismissRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
        }
    )

    TimeDurationDialog(
        visible = state.showDurationDialog,
        selectedTimeInSeconds = state.movie.durationMin.minutesToSeconds(),
        onPositiveClick = { durationInSeconds ->
            viewModel.onEvent(AddEditMovieEvent.ChangeDuration(durationInSeconds))
        },
        onCloseRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeDurationDialogVisibility)
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
                text = stringResource(R.string.title),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_title_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            ReadOnlyTextField(
                text = state.movie.title
            )
            ValueTitle(
                modifier = Modifier.padding(top = 25.dp),
                text = stringResource(R.string.release),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    viewModel.onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
                }
            )
            ReadOnlyTextField(
                text = state.movie.releaseDate ?: ""
            )
            ValueTitle(
                modifier = Modifier.padding(top = 25.dp),
                text = stringResource(R.string.duration),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_access_time_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    viewModel.onEvent(AddEditMovieEvent.ChangeDurationDialogVisibility)
                }
            )
            ReadOnlyTextField(
                text = if (state.movie.durationMin != 0L) {
                    state.movie.durationMin.toString() + stringResource(id = R.string._min)
                } else ""
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}