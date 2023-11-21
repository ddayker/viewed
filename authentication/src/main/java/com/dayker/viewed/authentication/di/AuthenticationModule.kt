package com.dayker.viewed.authentication.di

import com.dayker.viewed.authentication.data.client.GoogleAuthClient
import com.dayker.viewed.authentication.domain.client.AuthClient
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val authenticationModule = module {

    single<AuthClient> {
        GoogleAuthClient(
            oneTapClient = Identity.getSignInClient(androidContext()),
            signInRequest = get()
        )
    }

    single {
        BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("108653913355-3qs5nl7jqe893ta36439dubadbn31u5h.apps.googleusercontent.com")
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}