package com.dayker.viewed.watchedmovies.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage

@Composable
fun MovieImage(
    modifier: Modifier = Modifier,
    imageUri: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (imageUri == null) {
            DefaultMovieImage(modifier = Modifier.padding(horizontal = 20.dp))
        } else {
            AsyncImage(
                model = imageUri.toUri(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.FillHeight,
            )
        }
    }
}