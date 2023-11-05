package com.dayker.viewed.watchedmovies.feature.addeditmovie.presentation

import com.dayker.viewed.watchedmovies.common.domain.model.Movie

data class AddEditState(
    val movie: Movie = Movie(),
    val isEditing: Boolean = false,
    val selectedTabIndex: Int = 0,
    val showCalendarDialog: Boolean = false,
    val showDurationDialog: Boolean = false,
    val showGenresListDialog: Boolean = false,
    val showInputDialog: Boolean = false,
    val showSavingErrorDialog: Boolean = false,
    val showDeleteConfirmationDialog: Boolean = false,
)