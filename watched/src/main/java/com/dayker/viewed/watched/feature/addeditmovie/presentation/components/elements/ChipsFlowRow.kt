package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FlowRowChips(
    modifier: Modifier = Modifier,
    labels: List<String>,
    onClick: (Int) -> Unit
) {
    FlowRow(modifier = modifier.padding(vertical = 10.dp)) {
        labels.forEachIndexed { index, label ->
            InputChip(
                colors = InputChipDefaults.inputChipColors(containerColor = MaterialTheme.colorScheme.onSecondaryContainer),
                onClick = {
                    onClick(index)
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(vertical = 3.dp)
                    )
                },
                selected = true,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                trailingIcon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
            )
        }
    }
}
