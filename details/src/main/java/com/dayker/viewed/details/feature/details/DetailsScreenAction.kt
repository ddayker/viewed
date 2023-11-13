package com.dayker.viewed.details.feature.details

import android.content.IntentSender

sealed class DetailsScreenAction {

    data class ShowErrorMessage(val message: String) : DetailsScreenAction()
    data class ShowSignInRequest(val intentSender: IntentSender?) : DetailsScreenAction()
    object OpenAboutApp : DetailsScreenAction()
}