package com.dayker.viewed.authentication.domain.module

data class SignInResult(
    val data: User?,
    val errorMessage: String?
)