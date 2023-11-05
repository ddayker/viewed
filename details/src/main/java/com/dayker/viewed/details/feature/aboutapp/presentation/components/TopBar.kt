package com.dayker.viewed.details.feature.aboutapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dayker.details.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppTopBar(modifier: Modifier = Modifier, backButtonAction: () -> Unit) {
    Surface(modifier) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            ),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    IconButton(onClick = {
                        backButtonAction()
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.back_vector),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp),
                        )
                    }
                    Text(
                        text = stringResource(R.string.about),
                        modifier = Modifier.align(Alignment.TopCenter),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        )
    }
}