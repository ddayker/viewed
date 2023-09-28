package com.dayker.viewed.presentation.details.aboutapp

import android.content.Context
import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dayker.viewed.R
import com.dayker.viewed.presentation.details.aboutapp.components.AboutAppBottomBar
import com.dayker.viewed.presentation.details.aboutapp.components.AboutAppScreenContent
import com.dayker.viewed.presentation.details.aboutapp.components.AboutAppTopBar
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutAppScreen(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    onBackButtonClicked: () -> Unit,
    viewModel: AboutAppViewModel = getViewModel()
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val context = LocalContext.current
    val mailto = createBugReportMailto(context)
    val tagUrl = createTagUrl(context)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { intent ->
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AboutAppTopBar {
                onBackButtonClicked()
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
            onClickBugReportAction = { viewModel.onEvent(AboutAppEvent.ClickedBugReport(mailtoUrl = mailto)) },
            onClickTagAction = { viewModel.onEvent(AboutAppEvent.ClickedTag(intentUrl = tagUrl)) }
        )
    }
}

fun createBugReportMailto(context: Context): String {
    val subject = context.getString(R.string.viewed_bug_report)
    val email = context.getString(R.string.contact_email)
    val body = context.getString(
        R.string.app_version_sdk_device_problem,
        context.getString(R.string.app_version),
        Build.VERSION.SDK_INT.toString(),
        Build.MODEL
    )
    return context.getString(R.string.mailto_subject_body_to, subject, body, email)
}

fun createTagUrl(context: Context): String {
    return context.getString(R.string.linkedIn_link)
}



