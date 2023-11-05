package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopBar(
    modifier: Modifier = Modifier,
    title: String,
    showDeleteButton: Boolean = false,
    deleteButtonAction: () -> Unit,
    backButtonAction: () -> Unit
) {
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
                        text = title,
                        modifier = Modifier.align(Alignment.TopCenter),
                        style = MaterialTheme.typography.titleLarge
                    )
                    if (showDeleteButton) {
                        IconButton(
                            onClick = {
                                deleteButtonAction()
                            }, modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 15.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.round_delete_forever_24),
                                contentDescription = null,
                                modifier = Modifier.size(38.dp)
                            )
                        }
                    }

                }
            }
        )
    }
}