package com.dayker.viewed.presentation.watched.components

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
import com.dayker.viewed.R
import com.dayker.viewed.domain.model.Movie

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WatchedItem(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
    ) {
        Row {
            Column(modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                if (movie.imageURL.isNullOrEmpty()) {
                    DefaultImage()
                } else {
                    // TODO: load image from URL
                    DefaultImage()
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 15.dp, top = 15.dp, bottom = 20.dp)
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
                            style = MaterialTheme.typography.displaySmall,
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
                            text = movie.duration.toString(),
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(
                            text = stringResource(R.string.min),
                            style = MaterialTheme.typography.displaySmall,
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
                Text(
                    text = movie.viewingDate,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 10.dp)
                )
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