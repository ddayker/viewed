package com.dayker.viewed.details.feature.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dayker.viewed.core.ui.components.Animation
import com.dayker.viewed.core.ui.components.animatedBorder

@Composable
fun DetailsItem(
    modifier: Modifier = Modifier,
    title: String,
    animationId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .animatedBorder(
                    borderColors = listOf(
                        MaterialTheme.colorScheme.onTertiaryContainer,
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.onBackground
                    ),
                    backgroundColor = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(8.dp),
                    borderWidth = 2.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Animation(
                lottieRes = animationId,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.2f),
            )
            Text(
                text = title,
                modifier = Modifier.padding(start = 5.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 16.sp)
            )
        }
    }
}