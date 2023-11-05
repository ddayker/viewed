package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

@Composable
fun ReadOnlyTextField(
    text: String = "",
    textStyle: TextStyle = MaterialTheme.typography.displayLarge
) {
    val titleStartColor = MaterialTheme.colorScheme.onTertiaryContainer.toArgb()
    val titleEndColor = MaterialTheme.colorScheme.onSecondaryContainer
    val brush = remember {
        Brush.linearGradient(
            colors = listOf(
                Color(
                    green = titleStartColor.green,
                    red = titleStartColor.red,
                    blue = titleStartColor.blue,
                    alpha = titleStartColor.alpha
                ),
                Color(
                    green = titleEndColor.green,
                    red = titleEndColor.red,
                    blue = titleEndColor.blue,
                    alpha = titleEndColor.alpha
                )
            )
        )
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        enabled = false,
        readOnly = true,
        onValueChange = {},
        textStyle = textStyle.copy(brush = brush),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background,
        )
    )
}