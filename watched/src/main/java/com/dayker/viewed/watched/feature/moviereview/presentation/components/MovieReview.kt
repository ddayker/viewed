package com.dayker.viewed.watched.feature.moviereview.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.common.domain.model.Movie

@Composable
internal fun MovieReview(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    val scrollState = rememberScrollState()
    val columnHeight = remember { mutableIntStateOf(0) }
    val totalScrollableDistance = columnHeight.intValue - scrollState.maxValue
    val currentScrollableDistance = columnHeight.intValue - scrollState.value
    val percent: Float by animateFloatAsState(
        totalScrollableDistance.toFloat() / currentScrollableDistance.toFloat(),
        label = ""
    )
    LinearProgressIndicator(
        progress = percent,
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp),
        color = MaterialTheme.colorScheme.onTertiaryContainer,
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .verticalScroll(scrollState)
            .onGloballyPositioned { layoutCoordinates ->
                columnHeight.intValue = layoutCoordinates.size.height
            }
    ) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.displayLarge.copy(fontSize = 25.sp)
        )
        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.star),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
            )
            Text(
                modifier = Modifier.padding(start = 5.dp, top = 2.dp),
                text = movie.rating.toString(),
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.clock),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp, top = 2.dp),
                text = movie.durationMin.toString() + stringResource(R.string.minutes),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = stringResource(R.string.release_date_),
                    style = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = movie.releaseDate.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            TitledFlowRow(
                title = stringResource(R.string.director),
                items = movie.directors,
                modifier = Modifier.padding(start = 30.dp)
            )

        }
        Divider(modifier = Modifier.padding(vertical = 20.dp))
        Text(
            text = stringResource(R.string.review_),
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = movie.viewingDate.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = if (movie.review.trim()
                    .isEmpty()
            ) stringResource(R.string.empty_review) else movie.review,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        if (movie.genres.isNotEmpty() || movie.writers.isNotEmpty() || movie.stars.isNotEmpty()) {
            Divider(modifier = Modifier.padding(vertical = 20.dp))
        }
        TitledFlowRow(
            title = stringResource(R.string.genres),
            items = movie.genres
        )
        TitledFlowRow(
            title = stringResource(R.string.writers),
            items = movie.writers,
            modifier = Modifier.padding(top = 20.dp)
        )
        TitledFlowRow(
            title = stringResource(R.string.stars_),
            items = movie.stars,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}