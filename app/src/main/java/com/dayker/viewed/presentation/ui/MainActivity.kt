package com.dayker.viewed.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dayker.viewed.presentation.theme.AppTheme
import com.dayker.viewed.presentation.ui.aboutapp.AboutAppScreen
import com.dayker.viewed.presentation.ui.home.HomeScreen
import com.dayker.viewed.presentation.utils.NavigationConstants.ABOUT_APP
import com.dayker.viewed.presentation.utils.NavigationConstants.HOME

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface() {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = HOME) {
                        composable(HOME) {
                            HomeScreen {
                                navController.navigate(ABOUT_APP)
                            }
                        }
                        composable(ABOUT_APP) {
                            AboutAppScreen(windowSize = calculateWindowSizeClass(this@MainActivity)) {
                                navController.navigate(HOME)
                            }
                        }
                    }
                }
            }
        }
    }
}

