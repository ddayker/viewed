package com.dayker.viewed.details.feature.aboutapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.details.R
import com.dayker.viewed.core.ui.components.Animation

@Composable
fun PageTwoContentPortrait(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            PosterAnimation()
            AppDescriptionList(
                appDescription = getAppDescriptionResource(),
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
    }
}

@Composable
fun PageTwoContentLandscape(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PosterAnimation()
            AppDescriptionList(
                appDescription = getAppDescriptionResource(),
                modifier = Modifier.padding()
            )
        }
    }
}

@Composable
fun PosterAnimation(modifier: Modifier = Modifier) {
    Box(modifier, contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(id = R.drawable.background_info),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(20.dp)
                .height(330.dp)
                .clip(shape = RoundedCornerShape(36.dp)),
            alignment = Alignment.TopCenter
        )
        Animation(
            modifier = Modifier
                .padding(top = 20.dp)
                .size(380.dp),
            lottieRes = R.raw.animation_save
        )
    }
}

@Composable
fun AppDescriptionList(modifier: Modifier = Modifier, appDescription: List<Int>) {
    Column(
        horizontalAlignment = Alignment.Start, modifier = modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.save_the_movies_and_tv_shows_you_ve_watched),
            style = MaterialTheme.typography.displayLarge,
        )
        for (content in appDescription) {
            Text(
                text = stringResource(content),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

fun getAppDescriptionResource() =
    listOf(
        R.string.rate_them,
        R.string.write_reviews_and_take_notes,
        R.string.share_your_reviews_with_your_friends
    )