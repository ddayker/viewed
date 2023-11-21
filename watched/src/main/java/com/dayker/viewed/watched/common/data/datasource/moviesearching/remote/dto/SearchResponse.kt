package com.dayker.viewed.watched.common.data.datasource.moviesearching.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("Response")
    val response: String,
    @SerialName("Search")
    val movies: List<MovieResponse>,
    @SerialName("totalResults")
    val totalResults: String
)