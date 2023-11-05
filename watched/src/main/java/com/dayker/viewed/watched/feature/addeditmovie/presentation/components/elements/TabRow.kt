package com.dayker.viewed.watched.feature.addeditmovie.presentation.components.elements

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dayker.viewed.watched.R

@Composable
fun AddEditMovieTabRow(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.padding(top = 25.dp),
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = { tabPositions: List<TabPosition> ->
            Box(
                modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .padding(5.dp)
                    .fillMaxSize()
                    .border(
                        BorderStroke(3.dp, MaterialTheme.colorScheme.onTertiaryContainer),
                        RoundedCornerShape(10.dp)
                    )
            )
        }
    ) {
        AddEditRow.values().forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(index) },
                text = { Text(stringResource(id = tab.titleId)) },
                icon = {
                    Icon(
                        painter = painterResource(tab.iconId),
                        contentDescription = null
                    )
                }
            )
        }
    }
}

enum class AddEditRow(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int
) {
    Movie(R.string.movie, R.drawable.baseline_movie_24),
    Image(R.string.image, R.drawable.baseline_image_24),
    Review(R.string.review, R.drawable.baseline_rate_review_24),
    Details(R.string.details, R.drawable.baseline_saved_search_24)
}






