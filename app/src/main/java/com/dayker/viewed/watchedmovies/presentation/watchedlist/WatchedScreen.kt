package com.dayker.viewed.watchedmovies.presentation.watchedlist

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
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
import com.dayker.viewed.watchedmovies.presentation.navigation.WatchedNavGraphConstants.EMPTY_ID
import com.dayker.viewed.watchedmovies.presentation.navigation.WatchedScreen
import com.dayker.viewed.watchedmovies.presentation.watchedlist.components.WatchedList
import com.dayker.viewed.watchedmovies.presentation.watchedlist.components.WatchedTopBar
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchedListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: WatchedViewModel = getViewModel()
) {
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    viewModel.onEvent(WatchedScreenEvent.HideFab)
                }
                if (available.y > 1) {
                    viewModel.onEvent(WatchedScreenEvent.ShowFAB)

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
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            WatchedTopBar(
                scrollBehavior = scrollBehavior,
                sortButtonAction = {
                    viewModel.onEvent(WatchedScreenEvent.ToggleOrderSection)
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
                LargeFloatingActionButton(
                    onClick = {
                        val routeWithParams =
                            "${WatchedScreen.AddEditMovieScreen.route}/${EMPTY_ID}"
                        navController.navigate(routeWithParams)
                    },
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                    )
                }
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
                    viewModel.onEvent(WatchedScreenEvent.Order(order))
                }
            )
            if (state.isLoading) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                }
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



