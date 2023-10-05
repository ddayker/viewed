package com.dayker.viewed.domain.model

data class Movie(
    val title: String,
    val duration: Int,
    val rating: Double,
    val review: String,
    val viewingDate: String,
    val releaseDate: String,
    val imageURL: String? = null
)