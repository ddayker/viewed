package com.dayker.viewed.watchedmovies.feature.moviesearch.data.datasource.remote.dto


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