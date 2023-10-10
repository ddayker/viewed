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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R
import com.dayker.viewed.app.core.utils.toTime
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.AddEditMovieEvent
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.AddEditMovieViewModel
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.dialogs.CalendarPickerDialog
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.dialogs.InputTextDialog
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.MovieRating
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.ReadOnlyTextField
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.ValueTitle
import org.koin.androidx.compose.getViewModel

@Composable
fun ReviewTab(
    modifier: Modifier = Modifier,
    viewModel: AddEditMovieViewModel = getViewModel()
) {
    val uiState = viewModel.uiState.value
    val movieState = viewModel.movieState.value

    InputTextDialog(
        visible = uiState.showInputDialog,
        title = stringResource(R.string.write_your_review),
        text = movieState.review,
        onPositiveClick = { review ->
            viewModel.onEvent(AddEditMovieEvent.ChangeReview(review = review))
        },
        onCloseRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
        },
    )

    CalendarPickerDialog(
        visible = uiState.showCalendarDialog,
        pickedDate = movieState.releaseDate,
        onConfirm = { dateInMillis ->
            viewModel.onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
            viewModel.onEvent(AddEditMovieEvent.ChangeViewingDate(dateInMillis))
        },
        onDismissRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
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
                text = stringResource(R.string.viewing_date_),
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
                text = movieState.viewingDate?.toTime() ?: ""
            )
            MovieRating(
                modifier = Modifier.padding(top = 25.dp),
                text = stringResource(id = R.string.rating),
                rating = movieState.rating,
                onValueChange = { rating ->
                    viewModel.onEvent(AddEditMovieEvent.ChangeRating(rating))
                })
            ValueTitle(
                modifier = Modifier.padding(top = 10.dp),
                text = stringResource(R.string.review),
                buttonContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_border_color_24),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    viewModel.onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            ReadOnlyTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                text = movieState.review,
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}