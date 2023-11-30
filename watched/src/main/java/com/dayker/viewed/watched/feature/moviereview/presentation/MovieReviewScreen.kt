package com.dayker.viewed.watched.feature.moviereview.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dayker.viewed.core.presentation.Container
import com.dayker.viewed.core.ui.components.CircularLoading
import com.dayker.viewed.core.ui.components.MovieImage
import com.dayker.viewed.watched.common.platform.navigation.WatchedNavGraphConstants.SAVED_MOVIE_ID_KEY
import com.dayker.viewed.watched.common.platform.navigation.WatchedScreen
import com.dayker.viewed.watched.feature.moviereview.presentation.components.MovieInfoTopBar
import com.dayker.viewed.watched.feature.moviereview.presentation.components.MovieReview
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.VerticalTwoPaneStrategy
import org.koin.androidx.compose.getViewModel

@Composable
internal fun MovieReviewScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    navController: NavController,
    viewModel: MovieReviewViewModel = getViewModel()
) {
    Container(viewModel.actionFlow) { action ->
        when (action) {
            MovieReviewScreenAction.GoBack -> {
                navController.popBackStack(
                    navController.graph.findStartDestination().id,
                    inclusive = false
                )
            }

            is MovieReviewScreenAction.GoToEditing -> {
                val routeWithParams =
                    "${WatchedScreen.AddEditMovieScreen.route}?${SAVED_MOVIE_ID_KEY}=${action.id}"
                navController.navigate(route = routeWithParams) {
                    launchSingleTop = true
                }
            }
        }
    }
    val movie = viewModel.state.value.movie
    Scaffold(
        modifier = modifier,
        topBar = {
            MovieInfoTopBar(
                color = MaterialTheme.colorScheme.scrim,
                onBackClicked = {
                    viewModel.onEvent(MovieReviewEvent.BackClicked)
                },
                onEditClicked = {
                    movie.id?.let { viewModel.onEvent(MovieReviewEvent.EditClicked(movie.id)) }
                }
            )
        }
    ) {
        if (viewModel.state.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.scrim),
                contentAlignment = Alignment.Center
            ) {
                CircularLoading()
            }
        } else {
            TwoPane(
                modifier = Modifier.padding(it),
                first = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.scrim),
                        contentAlignment = Alignment.Center
                    ) {
                        MovieImage(
                            imageUri = movie.imageURL,
                            modifier = Modifier.padding(all = 15.dp)
                        )
                    }
                },
                second = {
                    MovieReview(movie = movie)
                },
                strategy =
                when (windowSize.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> VerticalTwoPaneStrategy(0.35f)
                    else -> {
                        HorizontalTwoPaneStrategy(0.35f)
                    }
                },
                displayFeatures = listOf()
            )
        }
    }
}