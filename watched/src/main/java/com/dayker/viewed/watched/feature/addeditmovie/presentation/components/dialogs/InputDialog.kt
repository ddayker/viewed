package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.BundleKeys.INPUT_KEY
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.input.models.InputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextDialog(
    title: String,
    text: String,
    visible: Boolean,
    onPositiveClick: (String) -> Unit,
    onCloseRequest: () -> Unit
) {
    val inputOptions = listOf(
        InputTextField(
            header = InputHeader(
                title = title,
                icon = IconSource(Icons.Filled.Edit)
            ),
            text = text,
            key = INPUT_KEY
        )
    )
    val state = rememberUseCaseState(
        visible = false,
        onCloseRequest = {
            onCloseRequest()
        })
    if (visible) {
        state.show()
    }
    InputDialog(
        state = state,
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { result ->
                onPositiveClick(result.getString(INPUT_KEY).toString())
            },
            positiveButton = SelectionButton(
                text = stringResource(id = R.string.confirm),
                type = ButtonStyle.TEXT,
            )
        )
    )
}
