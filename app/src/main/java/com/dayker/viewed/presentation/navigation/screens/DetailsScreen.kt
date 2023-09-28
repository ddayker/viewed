package com.dayker.viewed.presentation.navigation.screens

sealed class DetailsScreen(val route: String) {
    object AboutApp : DetailsScreen(route = "about_app_screen")
}