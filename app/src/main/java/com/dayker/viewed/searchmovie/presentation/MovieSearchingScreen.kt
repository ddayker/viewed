package com.dayker.viewed.searchmovie.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dayker.viewed.R
import com.dayker.viewed.common.presentation.components.CircularLoading
import com.dayker.viewed.searchmovie.presentation.components.MoviePoster
import com.dayker.viewed.searchmovie.presentation.components.SearchTopBar
import com.dayker.viewed.searchmovie.presentation.components.SearchingMessage
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    windowSize: WindowSizeClass,
    viewModel: MovieSearchingViewModel = getViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.actionFlow.collect() { event ->
            when (event) {
                MovieSearchingScreenAction.GoBack -> navController.navigateUp()
            }
        }
    }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            SearchTopBar(
                scrollBehavior = scrollBehavior,
                onCloseButtonClicked = {
                    viewModel.onEvent(MovieSearchingEvent.BackClicked)
                },
                query = state.query,
                onValueChange = { query ->
                    viewModel.onEvent(MovieSearchingEvent.Search(query))
                })
        }
    ) {
        Box(
            modifier = modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            when (state) {
                is MovieSearchingState.Empty -> {
                    SearchingMessage(
                        text = stringResource(R.string.no_movies_with_this_name_were_found),
                        lottieId = R.raw.animation_heart_break,
                        animationSize = 300.dp,
                        windowSizeClass = windowSize
                    )
                }

                is MovieSearchingState.Error -> {
                    SearchingMessage(
                        text = stringResource(R.string.something_went_wrong) +
                                stringResource(R.string.heck_your_internet_connection_or_add_a_movie_manually),
                        lottieId = R.raw.animation_heart_break,
                        animationSize = 300.dp,
                        windowSizeClass = windowSize
                    )
                }

                is MovieSearchingState.Loading -> {
                    CircularLoading(
                        modifier = Modifier.padding(top = 150.dp),
                    )
                }

                is MovieSearchingState.NoRequest -> {
                    SearchingMessage(
                        text = stringResource(R.string.find_a_movie_by_name),
                        lottieId = R.raw.animation_heart,
                        animationSize = 320.dp,
                        windowSizeClass = windowSize
                    )
                }

                is MovieSearchingState.ToShortQuery -> {
                    SearchingMessage(
                        text = stringResource(R.string.the_query_is_too_short),
                        lottieId = R.raw.animation_heart,
                        animationSize = 320.dp,
                        windowSizeClass = windowSize
                    )
                }

                is MovieSearchingState.Movies -> {
                    LazyColumn(modifier = modifier) {
                        items(state.movies) { movie ->
                            MoviePoster(movie = movie, onMovieClick = {})
                            Divider(
                                Modifier.padding(horizontal = 40.dp, vertical = 6.dp),
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
