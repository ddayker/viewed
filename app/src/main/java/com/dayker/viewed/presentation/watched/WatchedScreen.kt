package com.dayker.viewed.presentation.watched

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.dayker.viewed.presentation.watched.components.WatchedList
import com.dayker.viewed.presentation.watched.components.WatchedTopBar
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchedScreen(
    modifier: Modifier = Modifier,
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
                movies = state.movies
            )
        }
    }
}



