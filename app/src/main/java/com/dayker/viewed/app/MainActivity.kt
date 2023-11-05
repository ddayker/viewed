package com.dayker.viewed.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.dayker.viewed.app.navigation.graphs.RootNavigationGraph
import com.dayker.viewed.core.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface() {
                    val navController = rememberNavController()
                    val windowSize = calculateWindowSizeClass(this@MainActivity)
                    RootNavigationGraph(navController = navController, windowSize = windowSize)
                }
            }
        }
    }
}

