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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.common.utils.toLongDate
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieEvent
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditState
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.CalendarPickerDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.InputTextDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.MovieRating
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.ReadOnlyTextField
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.ValueTitle

@Composable
internal fun ReviewTab(
    modifier: Modifier = Modifier,
    state: AddEditState,
    onEvent: (AddEditMovieEvent) -> Unit
) {
    InputTextDialog(
        visible = state.showInputDialog,
        title = stringResource(R.string.write_your_review),
        text = state.movie.review,
        onPositiveClick = { review ->
            onEvent(AddEditMovieEvent.ChangeReview(review = review))
        },
        onCloseRequest = {
            onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
        },
    )

    CalendarPickerDialog(
        visible = state.showCalendarDialog,
        pickedDate = state.movie.viewingDate?.toLongDate(),
        onConfirm = { dateInMillis ->
            onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
            onEvent(AddEditMovieEvent.ChangeViewingDate(dateInMillis))
        },
        onDismissRequest = {
            onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
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
                    onEvent(AddEditMovieEvent.ChangeCalendarDialogVisibility)
                }
            )
            ReadOnlyTextField(
                text = state.movie.viewingDate ?: ""
            )
            MovieRating(
                modifier = Modifier.padding(top = 25.dp),
                text = stringResource(id = R.string.rating),
                rating = state.movie.rating,
                onValueChange = { rating ->
                    onEvent(AddEditMovieEvent.ChangeRating(rating))
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
                    onEvent(AddEditMovieEvent.ChangeInputDialogVisibility)
                }
            )
            ReadOnlyTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                text = state.movie.review,
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}