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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R
import java.time.LocalDate
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarPickerDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirm: (Long) -> Unit,
    pickedDate: Long?
) {
    val calendar = Calendar.getInstance()
    calendar.set(LocalDate.now().year, LocalDate.now().monthValue - 1, LocalDate.now().dayOfMonth)

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = calendar.timeInMillis,
    )

    var selectedDate by remember {
        mutableLongStateOf(pickedDate ?: calendar.timeInMillis)
    }

    if (visible) {
        DatePickerDialog(
            modifier = modifier,
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(onClick = {
                    selectedDate = datePickerState.selectedDateMillis!!
                    onConfirm(selectedDate)
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