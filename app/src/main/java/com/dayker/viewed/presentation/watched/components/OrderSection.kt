package com.dayker.viewed.presentation.watched.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
//    order: MoviesOrder = MoviesOrder.Viewing(OrderType.Descending),
//    onOrderChange: (MoviesOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.rating),
                selected = true,
                onClick = {},
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.viewing_date),
                selected = false,
                onClick = {},
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.release_date),
                selected = false,
                onClick = {},
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = stringResource(R.string.ascending),
                selected = true,
                onClick = {},
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.descending),
                selected = false,
                onClick = {},
            )
        }
    }
}