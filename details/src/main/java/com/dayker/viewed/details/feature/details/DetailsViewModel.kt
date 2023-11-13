package com.dayker.viewed.details.feature.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayker.viewed.authentication.client.GoogleAuthClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val authClient: GoogleAuthClient,
) : ViewModel() {
    private val _state = mutableStateOf(DetailsState())
    val state: State<DetailsState> = _state

    private val _actionFlow = MutableSharedFlow<DetailsScreenAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    init {
        _state.value = state.value.copy(user = authClient.getSignedInUser())
    }

    fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            DetailsScreenEvent.OnSignInClicked -> {
                viewModelScope.launch {
                    val signInIntentSender = authClient.signIn()
                    _actionFlow.emit(DetailsScreenAction.ShowSignInRequest(signInIntentSender))
                }
            }

            DetailsScreenEvent.OnClickAboutApp -> {
                viewModelScope.launch {
                    _actionFlow.emit(DetailsScreenAction.OpenAboutApp)
                }
            }

            DetailsScreenEvent.OnClickUserInfo -> {
                _state.value =
                    state.value.copy(isUserInfoExpanded = !state.value.isUserInfoExpanded)
            }

            DetailsScreenEvent.OnSignOutClicked -> {
                viewModelScope.launch {
                    authClient.signOut()
                    _state.value = state.value.copy(user = null, isUserInfoExpanded = false)
                }
            }

            is DetailsScreenEvent.OnSignInRequest -> {
                viewModelScope.launch {
                    val signInResult = authClient.signInWithIntent(
                        intent = event.intent ?: return@launch
                    )
                    _state.value = state.value.copy(user = signInResult.data)
                    val errorMessage = signInResult.errorMessage
                    if (!errorMessage.isNullOrEmpty()) {
                        _actionFlow.emit(DetailsScreenAction.ShowErrorMessage(errorMessage))
                    }
                }
            }
        }
    }
}



