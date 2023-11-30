package com.dayker.viewed.watched.feature.addeditmovie.presentation

import com.dayker.viewed.watched.common.domain.model.Movie

internal data class AddEditState(
    val movie: Movie = Movie(),
    val loading: Boolean = false,
    val isEditing: Boolean = false,
    val selectedTabIndex: Int = 0,
    val showCalendarDialog: Boolean = false,
    val showDurationDialog: Boolean = false,
    val showGenresListDialog: Boolean = false,
    val showInputDialog: Boolean = false,
    val showSavingErrorDialog: Boolean = false,
    val showDeleteConfirmationDialog: Boolean = false,
)