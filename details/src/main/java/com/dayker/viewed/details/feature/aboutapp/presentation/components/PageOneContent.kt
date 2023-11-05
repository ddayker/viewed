package com.dayker.viewed.details.feature.aboutapp.presentation.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.dayker.details.R
import com.dayker.viewed.core.ui.components.Animation

@Composable
fun PageOneContentPortrait(
    modifier: Modifier = Modifier, onClickTagAction: () -> Unit,
    onClickBugReportAction: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ViewedAnimatedLogo(
                modifier = Modifier.padding(80.dp),
                animationModifier = Modifier.size(200.dp)
            )
            DesignedTagAndBugReport(
                onClickBugReportAction = onClickBugReportAction,
                onClickTagAction = onClickTagAction
            )
        }
    }
}

@Composable
fun PageOneContentLandscape(
    modifier: Modifier = Modifier, onClickTagAction: () -> Unit,
    onClickBugReportAction: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        contentAlignment = Alignment.TopCenter,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ViewedAnimatedLogo(
                modifier = Modifier.padding(start = 100.dp),
                animationModifier = Modifier.size(100.dp)
            )
            DesignedTagAndBugReport(
                onClickBugReportAction = onClickBugReportAction,
                onClickTagAction = onClickTagAction
            )
        }
    }
}

@Composable
fun ViewedAnimatedLogo(modifier: Modifier = Modifier, animationModifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Animation(
            modifier = animationModifier,
            lottieRes = R.raw.animation_logo
        )
        Text(
            text = stringResource(R.string.viewed) + stringResource(R.string.app_version),
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = stringResource(R.string.you_are_what_you_watch),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
fun DesignedTagAndBugReport(
    modifier: Modifier = Modifier,
    onClickTagAction: () -> Unit,
    onClickBugReportAction: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Text(
                text = stringResource(R.string.designed_by),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = stringResource(R.string.dayker),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 5.dp, start = 3.dp)
                    .clickable(onClick = onClickTagAction),
                textDecoration = TextDecoration.Underline
            )
        }
        Row(modifier = Modifier.padding(top = 2.dp)) {
            Text(
                text = stringResource(R.string.found_a_bug),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = stringResource(R.string.contact_me),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 5.dp, start = 3.dp)
                    .clickable(onClick = onClickBugReportAction),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}