package com.dayker.viewed.watched.feature.movies.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayker.viewed.core.ui.components.MovieImage
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.common.domain.model.Movie

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun WatchedItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Long) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .clickable(onClick = {
                movie.id?.let { onMovieClick(it) }
            })
    ) {
        Row {
            Column(modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                MovieImage(
                    imageUri = movie.imageURL,
                    modifier = Modifier
                        .width(160.dp)
                        .padding(all = 20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 15.dp, top = 20.dp, bottom = 20.dp)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.bodyMedium)
                FlowRow(modifier = Modifier.padding(top = 10.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.rating_format, movie.rating),
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = stringResource(R.string._10),
                            style = MaterialTheme.typography.displaySmall.copy(fontSize = 14.sp),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 2.dp),
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = movie.durationMin.toString(),
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = stringResource(R.string._min),
                            style = MaterialTheme.typography.displaySmall.copy(fontSize = 14.sp),
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.clock),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 2.dp),
                            tint = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
                movie.viewingDate?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                Text(
                    text = movie.review,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 10.dp),
                    overflow = TextOverflow.Ellipsis
                )

            }
        }
    }
}