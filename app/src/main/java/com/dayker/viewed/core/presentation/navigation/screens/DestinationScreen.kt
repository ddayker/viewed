package com.dayker.viewed.core.presentation.navigation.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dayker.viewed.R

sealed class DestinationScreen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val inactiveIcon: Int,
    @DrawableRes val activeIcon: Int,
) {
    object Watched : DestinationScreen(
        route = "watched_screen",
        title = R.string.watched,
        activeIcon = R.drawable.outline_movie_24,
        inactiveIcon = R.drawable.baseline_movie_24,
    )

    object Discover : DestinationScreen(
        route = "discover_screen",
        title = R.string.discover,
        activeIcon = R.drawable.outline_local_movies_24,
        inactiveIcon = R.drawable.baseline_local_movies_24,
    )

    object Details : DestinationScreen(
        route = "details_screen",
        title = R.string.details,
        activeIcon = R.drawable.outline_interests_24,
        inactiveIcon = R.drawable.baseline_interests_24
    )
}
