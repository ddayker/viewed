package com.dayker.viewed.authentication.module

data class SignInResult(
    val data: User?,
    val errorMessage: String?
)