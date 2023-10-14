package com.dayker.viewed.watchedmovies.presentation.addeditmovie

data class AddEditUIState(
    val isEditing: Boolean = false,
    val selectedTabIndex: Int = 0,
    val showCalendarDialog: Boolean = false,
    val showDurationDialog: Boolean = false,
    val showGenresListDialog: Boolean = false,
    val showInputDialog: Boolean = false,
)