package com.dayker.viewed.watched.feature.addeditmovie.presentation

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.DeleteConfirmationDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.SavingErrorDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.AddEditMovieTabRow
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.AddEditRow
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.AddEditTopBar
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.DetailsTab
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.ImageTab
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.MovieTab
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.ReviewTab
import org.koin.androidx.compose.getViewModel

@Composable
fun AddEditMovieScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    navController: NavController,
    viewModel: AddEditMovieViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.actionFlow.collect() { event ->
            when (event) {
                is AddEditMovieScreenAction.SaveMovie -> {
                    if (event.isPossibleToSave) {
                        navController.navigateUp()
                    } else {
                        viewModel.onEvent(AddEditMovieEvent.ChangeSavingErrorDialogVisibility)
                    }
                }

                AddEditMovieScreenAction.DeleteMovie -> {
                    navController.navigateUp()
                }

                AddEditMovieScreenAction.ReturnBack -> {
                    navController.navigateUp()
                }
            }
        }
    }

    SavingErrorDialog(
        visible = viewModel.state.value.showSavingErrorDialog,
        onDismissRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeSavingErrorDialogVisibility)
        }
    )
    DeleteConfirmationDialog(
        visible = viewModel.state.value.showDeleteConfirmationDialog,
        onDismissRequest = {
            viewModel.onEvent(AddEditMovieEvent.ChangeDeleteConfirmationDialogVisibility)
        },
        onConfirmClick = {
            viewModel.onEvent(AddEditMovieEvent.DeleteMovie)
        }
    )
    Scaffold(
        modifier = modifier,
        topBar = {
            AddEditTopBar(
                title = if (viewModel.state.value.isEditing) stringResource(R.string.editing)
                else stringResource(R.string.adding),
                showDeleteButton = viewModel.state.value.isEditing,
                deleteButtonAction = {
                    viewModel.onEvent(AddEditMovieEvent.ChangeDeleteConfirmationDialogVisibility)
                },
                backButtonAction = {
                    viewModel.onEvent(AddEditMovieEvent.ReturnBack)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(bottom = 25.dp),
                onClick = {
                    viewModel.onEvent(AddEditMovieEvent.SaveMovie)
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
                selectedTabIndex = viewModel.state.value.selectedTabIndex,
                onTabClick = { index ->
                    viewModel.onEvent(AddEditMovieEvent.ChangeTabPosition(index))
                })

            when (viewModel.state.value.selectedTabIndex) {
                AddEditRow.Movie.ordinal -> MovieTab()
                AddEditRow.Image.ordinal -> ImageTab(windowSize = windowSize)
                AddEditRow.Review.ordinal -> ReviewTab()
                AddEditRow.Details.ordinal -> DetailsTab()
            }
        }
    }
}