package com.dayker.viewed.watched.feature.movies.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.core.ui.components.animatedBorder
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.feature.movies.presentation.WatchedMoviesScreenEvent


@Composable
internal fun UnsavedDataDialog(
    visible: Boolean,
    onEvent: (WatchedMoviesScreenEvent) -> Unit,
) {
    if (visible) {
        AlertDialog(
            modifier = Modifier.animatedBorder(
                borderColors = listOf(
                    MaterialTheme.colorScheme.onTertiaryContainer,
                    MaterialTheme.colorScheme.onBackground,
                    MaterialTheme.colorScheme.primary
                ),
                backgroundColor = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(30.dp),
                borderWidth = 2.dp,


                ),
            onDismissRequest = {},
            icon = {
                Icon(
                    Icons.Rounded.Warning,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
            },
            text = {
                Text(
                    stringResource(R.string.unsaved_data_was_found_on_the_device)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(WatchedMoviesScreenEvent.AddToAccountClicked)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(text = stringResource(R.string.add_to_google_account))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onEvent(WatchedMoviesScreenEvent.DeleteLocalClicked)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Text(text = stringResource(R.string.delete_permanently))
                }
            }
        )
    }
}