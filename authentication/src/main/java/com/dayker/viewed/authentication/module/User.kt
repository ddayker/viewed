package com.dayker.viewed.authentication.module

data class User(
    val userId: String,
    val email: String?,
    val username: String?,
    val profilePictureUrl: String?
)