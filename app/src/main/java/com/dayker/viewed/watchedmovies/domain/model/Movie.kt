package com.dayker.viewed.watchedmovies.domain.model

data class Movie(
    val title: String,
    val releaseDate: String,
    val duration: Long,
    val rating: Double,
    val review: String,
    val viewingDate: String,
    val imageURL: String? = null,
)