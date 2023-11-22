package com.dayker.viewed.core.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun MovieImage(
    modifier: Modifier = Modifier,
    imageUri: String? = null,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (imageUri.isNullOrEmpty()) {
            DefaultMovieImage()
        } else {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUri)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Error) {
                    DefaultMovieImage()
                }
                if (state is AsyncImagePainter.State.Loading) {
                    SubcomposeAsyncImageContent(
                        modifier = Modifier
                            .fillMaxHeight()
                            .shadow(
                                shape = RoundedCornerShape(20.dp),
                                elevation = 10.dp,
                                ambientColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                spotColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .shimmerEffect(),
                        contentScale = ContentScale.FillHeight
                    )
                } else {
                    SubcomposeAsyncImageContent(
                        modifier = Modifier
                            .fillMaxHeight()
                            .shadow(
                                shape = RoundedCornerShape(20.dp),
                                elevation = 10.dp,
                                ambientColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                spotColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            )
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillHeight
                    )

                }
            }
        }
    }
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.colorScheme.onTertiaryContainer,
                MaterialTheme.colorScheme.secondaryContainer,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}