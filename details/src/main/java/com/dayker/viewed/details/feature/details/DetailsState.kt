package com.dayker.viewed.details.feature.details

import com.dayker.viewed.authentication.domain.module.User

data class DetailsState(
    val user: User? = null,
    val isUserInfoExpanded: Boolean = false
)