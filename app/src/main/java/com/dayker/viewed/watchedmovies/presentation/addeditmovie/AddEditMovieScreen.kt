package com.dayker.viewed.watchedmovies.presentation.addeditmovie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dayker.viewed.R
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.AddEditMovieTabRow
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.AddEditRow
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.elements.AddEditTopBar
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.tabs.DetailsTab
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.tabs.ImageTab
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.tabs.MovieTab
import com.dayker.viewed.watchedmovies.presentation.addeditmovie.components.tabs.ReviewTab
import org.koin.androidx.compose.getViewModel

@Composable
fun AddEditMovieScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    navController: NavController,
    viewModel: AddEditMovieViewModel = getViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AddEditTopBar {
                navController.navigateUp()
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(bottom = 25.dp),
                onClick = {
                    navController.navigateUp()
                },
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                text = {
                    Text(
                        text = stringResource(R.string.save_movie),
                        color = MaterialTheme.colorScheme.background
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_save_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                    )
                })
        }
    ) { padding ->
        Column(modifier.padding(padding)) {
            AddEditMovieTabRow(
                selectedTabIndex = viewModel.uiState.value.selectedTabIndex,
                onTabClick = { index ->
                    viewModel.onEvent(AddEditMovieEvent.ChangeTabPosition(index))
                })

            when (viewModel.uiState.value.selectedTabIndex) {
                AddEditRow.Movie.ordinal -> MovieTab()
                AddEditRow.Image.ordinal -> ImageTab(windowSize = windowSize)
                AddEditRow.Review.ordinal -> ReviewTab()
                AddEditRow.Details.ordinal -> DetailsTab()
            }

        }
    }
}