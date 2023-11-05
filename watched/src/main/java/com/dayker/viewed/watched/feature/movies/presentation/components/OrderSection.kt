package com.dayker.viewed.watched.feature.movies.presentation.components

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
import com.dayker.viewed.watched.R
import com.dayker.viewed.watched.common.domain.util.MoviesOrder
import com.dayker.viewed.watched.common.domain.util.OrderType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    order: MoviesOrder = MoviesOrder.Rating(OrderType.Descending),
    onOrderChange: (MoviesOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.rating),
                selected = order is MoviesOrder.Rating,
                onClick = {
                    onOrderChange(
                        MoviesOrder.Rating(order.orderType)
                    )
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.viewing_date),
                selected = order is MoviesOrder.Viewing,
                onClick = {
                    onOrderChange(
                        MoviesOrder.Viewing(order.orderType)
                    )
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.release_date),
                selected = order is MoviesOrder.Release,
                onClick = {
                    onOrderChange(
                        MoviesOrder.Release(order.orderType)
                    )
                },
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = stringResource(R.string.ascending),
                selected = order.orderType is OrderType.Ascending,
                onClick = { onOrderChange(order.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.descending),
                selected = order.orderType is OrderType.Descending,
                onClick = { onOrderChange(order.copy(OrderType.Descending)) }
            )
        }
    }
}