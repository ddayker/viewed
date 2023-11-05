package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dayker.viewed.watched.R


@Composable
fun DeleteConfirmationDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            icon = { Icon(Icons.Rounded.Delete, contentDescription = null) },
            text = {
                Text(
                    stringResource(R.string.are_you_sure_you_want_to_delete_this_movie)
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmClick()
                        onDismissRequest()
                    }
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            },
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