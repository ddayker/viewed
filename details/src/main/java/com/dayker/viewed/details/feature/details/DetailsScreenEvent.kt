package com.dayker.viewed.details.feature.details

import android.content.Intent

sealed class DetailsScreenEvent {

    object OnSignInClicked : DetailsScreenEvent()
    object OnSignOutClicked : DetailsScreenEvent()
    object OnClickUserInfo : DetailsScreenEvent()
    object OnClickAboutApp : DetailsScreenEvent()
    data class OnSignInRequest(val intent: Intent?) : DetailsScreenEvent()
}