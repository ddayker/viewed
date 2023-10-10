package com.dayker.viewed.discovermovies.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R

@Composable
fun DiscoverScreen(modifier: Modifier = Modifier, name: String, onClick: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.baseline_saved_search_24),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}