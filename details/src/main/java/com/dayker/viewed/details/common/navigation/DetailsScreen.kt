package com.dayker.viewed.details.common.navigation

sealed class DetailsScreen(val route: String) {
    object AboutApp : DetailsScreen(route = "about_app_screen")
}