package com.dayker.viewed.authentication.domain.client

import android.content.Intent
import android.content.IntentSender
import com.dayker.viewed.authentication.domain.module.SignInResult
import com.dayker.viewed.authentication.domain.module.User

interface AuthClient {
    suspend fun initiateSignIn(): IntentSender?
    suspend fun completeSignIn(intent: Intent): SignInResult
    suspend fun signOut()
    fun getSignedInUser(): User?
}