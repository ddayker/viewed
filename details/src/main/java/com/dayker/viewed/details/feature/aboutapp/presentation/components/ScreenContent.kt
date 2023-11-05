package com.dayker.viewed.details.feature.aboutapp.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppScreenContent(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClickTagAction: () -> Unit,
    onClickBugReportAction: () -> Unit
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            AboutAppPortrait(
                modifier = modifier,
                pagerState = pagerState,
                onClickBugReportAction = onClickBugReportAction,
                onClickTagAction = onClickTagAction
            )
        }

        else -> {
            AboutAppLandscape(
                modifier = modifier,
                pagerState = pagerState,
                onClickBugReportAction = onClickBugReportAction,
                onClickTagAction = onClickTagAction
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppPortrait(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClickTagAction: () -> Unit,
    onClickBugReportAction: () -> Unit
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                PageOneContentPortrait(
                    modifier, onClickBugReportAction = onClickBugReportAction,
                    onClickTagAction = onClickTagAction
                )
            }

            1 -> {
                PageTwoContentPortrait(
                    modifier
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
    pagerState: PagerState,
    onClickTagAction: () -> Unit,
    onClickBugReportAction: () -> Unit
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                PageOneContentLandscape(
                    modifier, onClickBugReportAction = onClickBugReportAction,
                    onClickTagAction = onClickTagAction
                )
            }

            1 -> {
                PageTwoContentLandscape(
                    modifier
                )
            }

            2 -> {
                PageThreeContentLandscape(modifier)
            }

            else -> {}
        }
    }
}