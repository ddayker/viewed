package com.dayker.viewed.details.feature.aboutapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.dayker.details.R
import com.dayker.viewed.core.ui.components.Animation

@Composable
fun PageThreeContentPortrait(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimationPageThree(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(310.dp)
            )
            AppDescriptionPageThree()
        }
    }
}

@Composable
fun PageThreeContentLandscape(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimationPageThree(
                modifier = Modifier
                    .size(310.dp)
            )
            AppDescriptionPageThree()
        }
    }
}

@Composable
fun AnimationPageThree(modifier: Modifier = Modifier) {
    Animation(
        modifier = modifier,
        lottieRes = R.raw.amimation_favorite
    )
}

@Composable
fun AppDescriptionPageThree(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onTertiaryContainer)) {
                    append(stringResource(R.string.find_new))
                }
                append(stringResource(R.string.movies_and_shows_in_our_picks_and))
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onTertiaryContainer)) {
                    append(stringResource(R.string.save))
                }
                append(stringResource(R.string.them))
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onTertiaryContainer)) {
                    append(stringResource(R.string.to_watch_later))
                }
            },
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Text(
            text = stringResource(R.string.get_started_now),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
        )
    }
}