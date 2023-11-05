package com.dayker.viewed.watchedmovies.feature.movieinfo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.window.layout.DisplayFeature
import com.dayker.viewed.core.presentation.components.MovieImage
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.VerticalTwoPaneStrategy

@Composable
fun WatchedMovieScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    navController: NavController
) {
    Scaffold(
        topBar = {}
    ) {
        val displayFeatures = listOf<DisplayFeature>()
        TwoPane(
            modifier = Modifier.padding(it),
            first = {
                MovieImage()
            },
            second = {
                Text(
                    text = "",
                    Modifier
                        .fillMaxSize()
                        .background(color = Color.Blue)
                )
            },
            strategy = when (windowSize.widthSizeClass) {
                WindowWidthSizeClass.Compact -> VerticalTwoPaneStrategy(0.5f)
                else -> {
                    HorizontalTwoPaneStrategy(0.35f)
                }
            },
            displayFeatures = displayFeatures
        )
    }
}