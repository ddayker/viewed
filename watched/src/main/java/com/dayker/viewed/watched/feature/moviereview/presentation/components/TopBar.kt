package com.dayker.viewed.watched.feature.moviereview.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieInfoTopBar(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    onBackClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            containerColor = color
        ),
        modifier = modifier,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    onBackClicked()
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.back_vector),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                    )
                }
                IconButton(
                    onClick = {
                        onEditClicked()
                    },
                    modifier = Modifier.padding(end = 20.dp)

                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.editicon),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
        })
}