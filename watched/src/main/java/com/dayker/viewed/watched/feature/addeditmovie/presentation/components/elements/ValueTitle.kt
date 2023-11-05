package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ValueTitle(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    buttonContent: @Composable() () -> Unit
) {
    Row(
        modifier = modifier.padding(top = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = MaterialTheme.typography.displayLarge)
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(start = 20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.background,
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        ) {
            buttonContent()
        }
    }
}