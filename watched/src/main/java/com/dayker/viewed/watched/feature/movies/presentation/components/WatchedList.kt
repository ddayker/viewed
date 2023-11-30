package com.dayker.viewed.watched.feature.movies.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.common.domain.model.Movie
import com.dayker.viewed.watched.common.domain.util.MoviesOrder

@Composable
internal fun WatchedList(
    modifier: Modifier = Modifier,
    isOrderSectionVisible: Boolean,
    movies: List<Movie> = emptyList(),
    onMovieClick: (Long) -> Unit,
    onOrderChange: (MoviesOrder) -> Unit,
    order: MoviesOrder
) {
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
        if (movies.isNotEmpty()) {
            items(movies) { movie ->
                WatchedItem(
                    movie = movie,
                    onMovieClick = { id ->
                        onMovieClick(id)
                    }
                )
            }
        }
    }
}
