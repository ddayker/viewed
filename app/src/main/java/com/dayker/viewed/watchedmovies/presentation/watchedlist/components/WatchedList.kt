package com.dayker.viewed.watchedmovies.presentation.watchedlist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R
import com.dayker.viewed.watchedmovies.domain.model.Movie
import com.dayker.viewed.watchedmovies.domain.util.MoviesOrder

@Composable
fun WatchedList(
    modifier: Modifier = Modifier,
    isOrderSectionVisible: Boolean,
    movies: List<Movie> = emptyList(),
    onMovieClick: (Long) -> Unit,
    onOrderChange: (MoviesOrder) -> Unit,
    order: MoviesOrder
) {
    if (movies.isNotEmpty()) {
        LazyColumn(modifier = modifier) {
            item {
                AnimatedVisibility(
                    visible = isOrderSectionVisible,
                ) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 8.dp),
                        onOrderChange = {
                            onOrderChange(it)
                        },
                        order = order
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(movies) { movie ->
                WatchedItem(
                    movie = movie,
                    onMovieClick = { id ->
                        onMovieClick(id)
                    }
                )
            }
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_movie_message),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}