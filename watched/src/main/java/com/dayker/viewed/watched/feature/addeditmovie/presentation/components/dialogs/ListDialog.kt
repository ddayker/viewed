package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.BundleKeys.GENRES_LIST_KEY
import com.maxkeppeker.sheets.core.models.base.ButtonStyle
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.InputCheckboxGroup
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDialog(
    title: String,
    visible: Boolean,
    pickedGenres: List<String>,
    onPositiveClick: (List<String>) -> Unit,
    onCloseRequest: () -> Unit
) {
    val genres = listOf(
        stringResource(R.string.action),
        stringResource(R.string.adventure),
        stringResource(R.string.animation),
        stringResource(R.string.comedy),
        stringResource(R.string.crime),
        stringResource(R.string.documentary),
        stringResource(R.string.drama),
        stringResource(R.string.fantasy),
        stringResource(R.string.historical),
        stringResource(R.string.horror),
        stringResource(R.string.musical),
        stringResource(R.string.mystery),
        stringResource(R.string.romance),
        stringResource(R.string.science_fiction),
        stringResource(R.string.thriller),
        stringResource(R.string.war),
        stringResource(R.string.biographical),
        stringResource(R.string.sports),
        stringResource(R.string.western)
    )
    val enabledIndices = genres.mapIndexedNotNull { index, element ->
        if (pickedGenres.contains(element)) index else null
    }
    val inputOptions = listOf(
        InputCheckboxGroup(
            header = InputHeader(
                title = title
            ),
            key = GENRES_LIST_KEY,
            items = genres,
            enabledIndices = enabledIndices
        ),
    )
    val state = rememberUseCaseState(
        visible = false,
        onCloseRequest = {
            onCloseRequest()
        }
    )
    if (visible) {
        state.show()
    }
    InputDialog(
        state = state,
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { result ->
                val indexes = result.getIntArray(GENRES_LIST_KEY)
                val newPickedGenres = genres.filterIndexed { index, _ ->
                    indexes!!.contains(index)
                }
                onPositiveClick(newPickedGenres)
            },
            positiveButton = SelectionButton(
                text = stringResource(id = R.string.confirm),
                type = ButtonStyle.TEXT,
            )
        )
    )
}