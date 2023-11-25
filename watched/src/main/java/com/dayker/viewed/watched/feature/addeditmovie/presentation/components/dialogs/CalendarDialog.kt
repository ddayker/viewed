package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPickerDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirm: (Long) -> Unit,
    pickedDate: Long?
) {
    val dateTime = LocalDateTime.now()
    if (visible) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = if (pickedDate != null) pickedDate + TimeUnit.MILLISECONDS.convert(
                1,
                TimeUnit.DAYS
            ) else dateTime.toMillis(),
            yearRange = 1900..dateTime.year
        )
        DatePickerDialog(
            modifier = modifier,
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm(datePickerState.selectedDateMillis!!)
                }) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismissRequest()
                }) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    selectedDayContentColor = MaterialTheme.colorScheme.background,
                    dayInSelectionRangeContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    dayInSelectionRangeContentColor = MaterialTheme.colorScheme.background,
                    todayDateBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    todayContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
            )
        }
    }
}

fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()