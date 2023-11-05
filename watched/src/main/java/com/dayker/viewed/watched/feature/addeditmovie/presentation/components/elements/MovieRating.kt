package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRating(
    modifier: Modifier = Modifier,
    text: String,
    rating: Float,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(text = text, style = MaterialTheme.typography.displayLarge)
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = stringResource(R.string.rating_format, rating.toString()),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = stringResource(R.string._10),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        {
            val color = MaterialTheme.colorScheme.onTertiaryContainer
            Slider(
                value = rating,
                onValueChange = { value ->
                    onValueChange(round(value * 10) / 10)
                },
                valueRange = 0.0f..10.0f,
                colors = SliderDefaults.colors(
                    thumbColor = color,
                    activeTrackColor = color,
                    activeTickColor = color,
                    inactiveTickColor = color,
                    disabledThumbColor = color,
                    disabledActiveTrackColor = color,
                    disabledActiveTickColor = color,
                    disabledInactiveTrackColor = color,
                    disabledInactiveTickColor = color
                ),
                thumb = {
                    Icon(
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .size(40.dp),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            )
        }
    }
}