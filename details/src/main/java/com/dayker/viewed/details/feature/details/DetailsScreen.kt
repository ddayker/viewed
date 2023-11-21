package com.dayker.viewed.details.feature.details

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dayker.details.R
import com.dayker.viewed.core.presentation.Container
import com.dayker.viewed.core.ui.components.GoogleSignInButton
import com.dayker.viewed.details.common.navigation.DetailsScreen
import com.dayker.viewed.details.feature.details.components.DetailsItem
import com.dayker.viewed.details.feature.details.components.UserInfo
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailsViewModel = getViewModel(),
) {
    val user = viewModel.state.value.user
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                viewModel.onEvent(DetailsScreenEvent.OnSignInRequest(intent = result.data))
            }
        }
    )
    Container(viewModel.actionFlow) { action ->
        when (action) {
            is DetailsScreenAction.ShowErrorMessage -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = action.message,
                        duration = SnackbarDuration.Short
                    )
                }
            }

            is DetailsScreenAction.ShowSignInRequest -> {
                launcher.launch(
                    IntentSenderRequest.Builder(
                        action.intentSender ?: return@Container
                    ).build()
                )
            }


            DetailsScreenAction.OpenAboutApp -> {
                navController.navigate(DetailsScreen.AboutApp.route)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Snackbar(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                ) {
                    Text(
                        text = it.visuals.message,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 13.sp),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                DetailsItem(modifier = Modifier.padding(horizontal = 35.dp, vertical = 20.dp),
                    title = stringResource(R.string.about_viewed),
                    animationId = R.raw.animation_logo,
                    onClick = {
                        viewModel.onEvent(DetailsScreenEvent.OnClickAboutApp)
                    }
                )
                AnimatedVisibility(user == null) {
                    GoogleSignInButton(
                        modifier = Modifier.padding(
                            horizontal = 35.dp,
                        ),
                        onClick = {
                            viewModel.onEvent(DetailsScreenEvent.OnSignInClicked)
                        }
                    )
                }
                AnimatedVisibility(user != null) {
                    if (user != null) {
                        UserInfo(
                            modifier = Modifier.padding(
                                horizontal = 35.dp,
                            ),
                            user = user,
                            isExpanded = viewModel.state.value.isUserInfoExpanded,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}