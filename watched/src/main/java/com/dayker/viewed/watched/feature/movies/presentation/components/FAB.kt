package com.dayker.viewed.watched.feature.movies.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R

@Composable
fun AddingFAB(
    onManuallyClick: () -> Unit,
    onSearchClick: () -> Unit,
    onFABClick: () -> Unit,
    isExtended: Boolean
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End,
    ) {
        AnimatedVisibility(visible = isExtended) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                FABOption(
                    textId = R.string.manually,
                    imageId = R.drawable.round_art_track_24,
                    onClick = {
                        onManuallyClick()
                    })
                Spacer(modifier = Modifier.height(10.dp))
                FABOption(
                    textId = R.string.by_search,
                    imageId = R.drawable.round_manage_search_24,
                    onClick = {
                        onSearchClick()
                    })
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        val color by animateColorAsState(
            targetValue = if (isExtended) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.onTertiaryContainer,
            animationSpec = tween(durationMillis = 1000), label = ""
        )
        val rotation by animateFloatAsState(
            targetValue = if (isExtended) 45f else 0f,
            animationSpec = tween(durationMillis = 1000), label = ""
        )
        FloatingActionButton(
            onClick = {
                onFABClick()
            },
            containerColor = color,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .animateContentSize()
                .height(if (isExtended) 120.dp else 55.dp),
            contentColor = MaterialTheme.colorScheme.background
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_add_24),
                contentDescription = null,
                modifier = Modifier.rotate(rotation)
            )
        }
    }
}

@Composable
fun FABOption(
    modifier: Modifier = Modifier,
    @StringRes textId: Int,
    @DrawableRes imageId: Int,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = stringResource(textId),
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.labelMedium
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(textId),
                tint = MaterialTheme.colorScheme.background,
            )
        },
        onClick = {
            onClick()
        },
        expanded = true,
        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = modifier
    )
}





