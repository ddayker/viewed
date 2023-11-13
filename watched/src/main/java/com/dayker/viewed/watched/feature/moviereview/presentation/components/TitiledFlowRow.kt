package com.dayker.viewed.watched.feature.moviereview.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TitledFlowRow(
    modifier: Modifier = Modifier,
    title: String = "",
    items: List<String>
) {
    if (items.isNotEmpty()) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium
            )
            FlowRow {
                items.forEach { item ->
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(top = 6.dp, end = 6.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(vertical = 2.dp, horizontal = 2.dp)
                    )
                }
            }
        }
    }
}