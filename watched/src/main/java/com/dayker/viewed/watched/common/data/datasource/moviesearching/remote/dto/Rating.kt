package com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerialName("Source")
    val source: String,
    @SerialName("Value")
    val value: String
)