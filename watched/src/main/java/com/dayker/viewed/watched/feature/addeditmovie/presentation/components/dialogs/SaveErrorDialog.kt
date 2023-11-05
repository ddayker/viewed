package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R

@Composable
fun SavingErrorDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            icon = { Icon(Icons.Rounded.Warning, contentDescription = null) },
            text = {
                Text(
                    stringResource(R.string.fill_in_the_required_fields_title_duration_viewing_date)
                )
            },
            confirmButton = {},
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }
}