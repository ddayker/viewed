package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.duration.DurationDialog
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import com.maxkeppeler.sheets.duration.models.DurationSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeDurationDialog(
    visible: Boolean,
    onPositiveClick: (Long) -> Unit,
    onCloseRequest: () -> Unit,
    selectedTimeInSeconds: Long
) {
    val state = rememberUseCaseState(
        visible = false,
        onCloseRequest = {
            onCloseRequest()
        })
    if (visible) {
        state.show()
    }
    DurationDialog(
        state = state,
        selection = DurationSelection(
            onPositiveClick = { newTimeInSeconds ->
                onPositiveClick(newTimeInSeconds)
            },
            positiveButton = SelectionButton(
                text = stringResource(id = R.string.confirm),
                type = ButtonStyle.TEXT,
            ),
        ),
        config = DurationConfig(
            timeFormat = DurationFormat.HH_MM,
            currentTime = selectedTimeInSeconds
        )
    )
}