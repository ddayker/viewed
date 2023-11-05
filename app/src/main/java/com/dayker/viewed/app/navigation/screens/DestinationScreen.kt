package com.dayker.viewed.app.navigation.screens

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

    object Network : DestinationScreen(
        route = "network_screen",
        title = R.string.network,
        activeIcon = R.drawable.outline_group_24,
        inactiveIcon = R.drawable.baseline_group_24,
    )

    object Details : DestinationScreen(
        route = "details_screen",
        title = R.string.details,
        activeIcon = R.drawable.outline_interests_24,
        inactiveIcon = R.drawable.baseline_interests_24
    )
}
