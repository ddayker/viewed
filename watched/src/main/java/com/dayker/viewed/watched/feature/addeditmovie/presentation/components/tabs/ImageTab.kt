package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader.*
import com.dayker.viewed.core.ui.components.MovieImage
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditMovieEvent
import com.dayker.viewed.watched.feature.addeditmovie.presentation.AddEditState
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.VerticalTwoPaneStrategy

@SuppressLint("Recycle")
@Composable
internal fun ImageTab(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    state: AddEditState,
    onEvent: (AddEditMovieEvent) -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri == null) return@rememberLauncherForActivityResult
            onEvent(AddEditMovieEvent.ChangeImage(uri.toString()))
        }
    )

    TwoPane(
        modifier = modifier,
        first = {
            MovieImage(
                imageUri = state.movie.imageURL,
                modifier = Modifier.padding(all = 20.dp)
            )
        },
        second = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Button(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .size(90.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.background,
                        containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_download_24),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        },
        strategy = when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> VerticalTwoPaneStrategy(0.6f)
            else -> {
                HorizontalTwoPaneStrategy(0.6f)
            }
        },
        displayFeatures = listOf()
    )
}