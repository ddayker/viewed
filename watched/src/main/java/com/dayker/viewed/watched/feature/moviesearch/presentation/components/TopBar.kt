package com.dayker.viewed.watched.feature.moviesearch.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onCloseButtonClicked: () -> Unit,
    query: String,
    onValueChange: (String) -> Unit
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        onCloseButtonClicked()
                    }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.close_icon),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight(),
                        )
                    }
                    SearchTextField(
                        modifier = modifier.padding(
                            start = 5.dp,
                            end = 20.dp
                        ),
                        query = query,
                        onValueChange = { newQuery ->
                            onValueChange(newQuery)
                        }
                    )
                }
            },
            scrollBehavior = scrollBehavior,
        )
    }
}
