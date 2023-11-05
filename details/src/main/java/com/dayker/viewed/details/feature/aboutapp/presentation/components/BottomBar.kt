package com.dayker.viewed.details.feature.aboutapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dayker.details.R

@Composable
fun AboutAppBottomBar(modifier: Modifier = Modifier, currentPageIndex: Int, pageCount: Int) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = currentPageIndex != pageCount - 1
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.swipe_to_find_out_more),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
        var animationPlayed by rememberSaveable {
            mutableStateOf(false)
        }
        val currentPercentage = animateFloatAsState(
            targetValue =
            if (animationPlayed) ((currentPageIndex + 1).toFloat() / pageCount) else 0f,
            animationSpec = tween(durationMillis = 1000), label = ""
        )
        LaunchedEffect(key1 = true) {
            animationPlayed = true
        }
        LinearProgressIndicator(
            progress = currentPercentage.value,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}