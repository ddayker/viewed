package com.dayker.viewed.watched.feature.moviesearch.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayker.viewed.core.ui.components.MovieImage
import com.dayker.viewed.watched.common.domain.model.MoviePoster

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MoviePoster(
    modifier: Modifier = Modifier,
    movie: MoviePoster,
    onMovieClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .clickable(onClick = {
                onMovieClick(movie.id)
            })
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier.height(360.dp), contentAlignment = Alignment.TopCenter) {
                MovieImage(
                    imageUri = movie.poster,
                    modifier = Modifier
                        .padding(all = 10.dp)
                )
            }
            FlowRow(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 7.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(movie.title)
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp
                            )
                        ) {
                            append(" (${movie.year})")
                        }
                    },
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}