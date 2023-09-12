package com.dayker.viewed.presentation.ui.aboutapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R
import com.dayker.viewed.presentation.ui.aboutapp.components.AboutAppBottomBar
import com.dayker.viewed.presentation.ui.aboutapp.components.AboutAppTopBar
import com.dayker.viewed.presentation.ui.aboutapp.components.PageOneContentLandscape
import com.dayker.viewed.presentation.ui.aboutapp.components.PageOneContentPortrait
import com.dayker.viewed.presentation.ui.aboutapp.components.PageThreeContentLandscape
import com.dayker.viewed.presentation.ui.aboutapp.components.PageThreeContentPortrait
import com.dayker.viewed.presentation.ui.aboutapp.components.PageTwoContentLandscape
import com.dayker.viewed.presentation.ui.aboutapp.components.PageTwoContentPortrait


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    backButtonAction: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val appDescriptionFromResources =
        listOf(
            R.string.rate_them,
            R.string.write_reviews_and_take_notes,
            R.string.share_your_reviews_with_your_friends
        )

    Scaffold(
        modifier = modifier,
        topBar = {
            AboutAppTopBar {
                backButtonAction()
            }
        },
        bottomBar = {
            AboutAppBottomBar(
                currentPageIndex = pagerState.currentPage,
                pageCount = pagerState.pageCount,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            )
        }
    ) { padding ->
        AboutAppScreenContent(
            modifier = Modifier.padding(padding),
            windowSize = windowSize,
            pagerState = pagerState,
            appDescriptionFromResources = appDescriptionFromResources
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppScreenContent(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    appDescriptionFromResources: List<Int>
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AboutAppPortrait(
                modifier = modifier,
                appDescription = appDescriptionFromResources,
                pagerState = pagerState
            )
        }

        WindowWidthSizeClass.Expanded -> {
            AboutAppLandscape(
                modifier = modifier,
                appDescription = appDescriptionFromResources,
                pagerState = pagerState
            )
        }

        else -> {
            AboutAppLandscape(
                modifier = modifier,
                appDescription = appDescriptionFromResources,
                pagerState = pagerState
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppPortrait(
    modifier: Modifier = Modifier,
    appDescription: List<Int>,
    pagerState: PagerState,
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                PageOneContentPortrait(modifier)
            }

            1 -> {
                PageTwoContentPortrait(
                    modifier,
                    appDescription = appDescription
                )
            }

            2 -> {
                PageThreeContentPortrait(modifier)
            }

            else -> {}
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppLandscape(
    modifier: Modifier = Modifier,
    appDescription: List<Int>,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                PageOneContentLandscape(modifier)
            }

            1 -> {
                PageTwoContentLandscape(
                    modifier,
                    appDescription = appDescription
                )
            }

            2 -> {
                PageThreeContentLandscape(modifier)
            }

            else -> {}
        }
    }
}


