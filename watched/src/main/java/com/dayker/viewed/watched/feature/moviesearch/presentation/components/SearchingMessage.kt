package com.dayker.viewed.watched.feature.moviesearch.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dayker.viewed.core.ui.components.Animation

@Composable
fun SearchingMessage(
    modifier: Modifier = Modifier,
    text: String = "",
    lottieId: Int,
    animationSize: Dp = 200.dp,
    windowSizeClass: WindowSizeClass
) {
    AnimatedVisibility(visible = true) {
        Column(
            modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(visible = (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact)) {
                Animation(
                    modifier = Modifier.size(animationSize),
                    lottieRes = lottieId
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp),
                textAlign = TextAlign.Center
            )

            Divider(
                Modifier.width(200.dp),
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

        }
    }
}