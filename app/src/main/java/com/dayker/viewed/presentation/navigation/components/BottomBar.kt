package com.dayker.viewed.presentation.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.dayker.viewed.presentation.navigation.screens.DestinationScreen

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    screens: List<DestinationScreen>,
    elementOnClick: (String) -> Unit
) {
    val isDestination = screens.any { it.route == currentDestination?.route }
    AnimatedVisibility(
        visible = isDestination,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar(
            modifier = modifier,
            windowInsets = NavigationBarDefaults.windowInsets,
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    elementOnClick = elementOnClick
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: DestinationScreen,
    currentDestination: NavDestination?,
    elementOnClick: (String) -> Unit
) {
    val isActive = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    NavigationBarItem(
        label = {
            Text(text = stringResource(id = screen.title))
        },
        icon = {
            FlipIcon(
                isActive = isActive,
                activeIcon = painterResource(screen.activeIcon),
                inactiveIcon = painterResource(screen.inactiveIcon),
            )
        },
        selected = isActive,
        alwaysShowLabel = false,
        onClick = { elementOnClick(screen.route) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.tertiaryContainer,
            selectedTextColor = MaterialTheme.colorScheme.tertiaryContainer,
            indicatorColor = MaterialTheme.colorScheme.background
        )
    )
}