package com.dayker.viewed.watched.feature.addeditmovie.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dayker.viewed.core.presentation.Container
import com.dayker.viewed.core.ui.components.CircularLoading
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.common.platform.navigation.WatchedScreen
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.DeleteConfirmationDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.dialogs.SavingErrorDialog
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.AddEditMovieTabRow
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.AddEditRow
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements.AddEditTopBar
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.DetailsTab
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.ImageTab
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.MovieTab
import com.dayker.viewed.watched.feature.addeditmovie.presentation.components.tabs.ReviewTab
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
internal fun AddEditMovieScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    navController: NavController,
    viewModel: AddEditMovieViewModel = getViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = stringResource(R.string.movie_not_found)
    Container(viewModel.actionFlow) { action ->
        when (action) {
            is AddEditMovieScreenAction.SaveMovie -> {
                if (action.isPossibleToSave) {
                    val routeWithParams =
                        "${WatchedScreen.WatchedMovieScreen.route}/${action.id}"
                    val previousDestination = navController.previousBackStackEntry?.destination
                    if (previousDestination?.id != navController.graph.findStartDestination().id) {
                        navController.navigate(routeWithParams) {
                            previousDestination?.id?.let {
                                popUpTo(it) {
                                    inclusive = true
                                }
                            }
                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(routeWithParams) {
                            launchSingleTop = true
                            navController.currentDestination?.let {
                                popUpTo(it.id) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                } else {
                    viewModel.onEvent(AddEditMovieEvent.ChangeSavingErrorDialogVisibility)
                }
            }

            AddEditMovieScreenAction.DeleteMovie -> {
                navController.popBackStack(
                    navController.graph.findStartDestination().id,
                    inclusive = false
                )
            }

            AddEditMovieScreenAction.ReturnBack -> {
                navController.navigateUp()
            }

            AddEditMovieScreenAction.ShowError -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
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
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        },
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
            if (viewModel.state.value.loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularLoading()
                }
            } else {
                AddEditMovieTabRow(
                    selectedTabIndex = viewModel.state.value.selectedTabIndex,
                    onTabClick = { index ->
                        viewModel.onEvent(AddEditMovieEvent.ChangeTabPosition(index))
                    })
                when (viewModel.state.value.selectedTabIndex) {
                    AddEditRow.Movie.ordinal -> MovieTab(
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent
                    )

                    AddEditRow.Image.ordinal -> ImageTab(
                        windowSize = windowSize,
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent
                    )

                    AddEditRow.Review.ordinal -> ReviewTab(
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent
                    )

                    AddEditRow.Details.ordinal -> DetailsTab(
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}