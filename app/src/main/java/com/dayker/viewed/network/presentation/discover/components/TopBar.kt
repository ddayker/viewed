package com.dayker.viewed.network.presentation.discover.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    isSearching: Boolean = false,
    onSearchButtonClick: () -> Unit,
) {
    var showSearchBar by remember {
        mutableStateOf(isSearching)
    }
    Surface(modifier) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            ),
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        onSearchButtonClick()
                        showSearchBar = !showSearchBar
                    }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.search_icon),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight(),
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            },
            scrollBehavior = scrollBehavior,
        )
    }
}
