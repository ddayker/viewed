package com.dayker.viewed.app.navigation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.dayker.viewed.app.navigation.screens.DestinationScreen
import com.dayker.viewed.core.ui.components.FlipIcon

@Composable
fun AppNavigationRail(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    screens: List<DestinationScreen>,
    elementOnClick: (String) -> Unit
) {
    val isDestination = screens.any { it.route == currentDestination?.route }
    AnimatedVisibility(
        visible = isDestination
    ) {
        NavigationRail(
            modifier = modifier,
            windowInsets = NavigationRailDefaults.windowInsets,
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
fun ColumnScope.AddItem(
    screen: DestinationScreen,
    currentDestination: NavDestination?,
    elementOnClick: (String) -> Unit
) {
    val isActive = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    NavigationRailItem(
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
        onClick = {
            if (!isActive) {
                elementOnClick(screen.route)
            }
        },
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onTertiary,
            selectedTextColor = MaterialTheme.colorScheme.onTertiary,
            indicatorColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier.padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
    )
}