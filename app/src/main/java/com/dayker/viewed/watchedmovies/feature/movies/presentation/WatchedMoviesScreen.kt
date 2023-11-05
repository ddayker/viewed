package com.dayker.viewed.watchedmovies.feature.movies.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dayker.viewed.R
import com.dayker.viewed.core.presentation.components.CircularLoading
import com.dayker.viewed.watchedmovies.common.platform.navigation.WatchedNavGraphConstants.EMPTY_ID
import com.dayker.viewed.watchedmovies.common.platform.navigation.WatchedScreen
import com.dayker.viewed.watchedmovies.feature.movies.presentation.components.AddingFAB
import com.dayker.viewed.watchedmovies.feature.movies.presentation.components.WatchedList
import com.dayker.viewed.watchedmovies.feature.movies.presentation.components.WatchedTopBar
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchedMoviesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: WatchedMoviesViewModel = getViewModel()
) {
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    viewModel.onEvent(WatchedMoviesScreenEvent.HideFAB)
                }
                if (available.y > 1) {
                    viewModel.onEvent(WatchedMoviesScreenEvent.ShowFAB)
                }
                return Offset.Zero
            }
        }
    }
    Scaffold(
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            WatchedTopBar(
                scrollBehavior = scrollBehavior,
                sortButtonAction = {
                    viewModel.onEvent(WatchedMoviesScreenEvent.ToggleOrderSection)
                },
                watchedCount = state.movies.size,
                isOrderSelectionVisible = state.isOrderSelectionVisible
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = state.isFABVisible,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                AddingFAB(
                    isExtended = state.isFABExtended,
                    onFABClick = {
                        viewModel.onEvent(WatchedMoviesScreenEvent.ExtendFAB)
                    },
                    onManuallyClick = {
                        val routeWithParams =
                            "${WatchedScreen.AddEditMovieScreen.route}/${EMPTY_ID}"
                        navController.navigate(routeWithParams)
                    },
                    onSearchClick = {
                        navController.navigate(WatchedScreen.SearchScreen.route)
                    },
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
        ) {
            WatchedList(
                modifier = Modifier.nestedScroll(nestedScrollConnection),
                isOrderSectionVisible = state.isOrderSelectionVisible,
                movies = state.movies,
                onMovieClick = { id ->
                    val routeWithParams = "${WatchedScreen.AddEditMovieScreen.route}/${id}"
                    navController.navigate(routeWithParams)

                },
                order = state.moviesOrder,
                onOrderChange = { order ->
                    viewModel.onEvent(WatchedMoviesScreenEvent.Order(order))
                }
            )
            if (state.isLoading) {
                CircularLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp),
                )
            }
            if (state.isEmpty) {
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
    }
}



