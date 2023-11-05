package com.dayker.viewed.watched.feature.movies.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayker.viewed.core.ui.components.FlipIcon
import com.dayker.viewed.watched.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchedTopBar(
    modifier: Modifier = Modifier,
    sortButtonAction: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    watchedCount: Int,
    isOrderSelectionVisible: Boolean
) {
    Surface(modifier) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            ),
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        sortButtonAction()
                    }) {
                        FlipIcon(
                            isActive = isOrderSelectionVisible,
                            activeIcon = painterResource(R.drawable.sort_back_icon),
                            inactiveIcon = painterResource(R.drawable.sort_vector),
                            modifier = Modifier.fillMaxHeight(),
                        )
                    }
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.your_movies),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = stringResource(R.string.watched_count_format, watchedCount),
                        style = MaterialTheme.typography.displayLarge,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            },
            scrollBehavior = scrollBehavior,
        )
    }
}
