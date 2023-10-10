package com.dayker.viewed.watchedmovies.presentation.addeditmovie

data class AddEditUIState(
    val selectedTabIndex: Int = 0,
    val showCalendarDialog: Boolean = false,
    val showDurationDialog: Boolean = false,
    val showGenresListDialog: Boolean = false,
    val showInputDialog: Boolean = false,
)