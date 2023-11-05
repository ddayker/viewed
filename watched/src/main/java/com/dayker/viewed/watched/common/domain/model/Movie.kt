package com.dayker.viewed.watched.common.domain.model

data class Movie(
    val id: Long? = null,
    val title: String = "",
    val releaseDate: String? = null,
    val durationMin: Long = 0,
    val rating: Float = 7.5f,
    val review: String = "",
    val viewingDate: String? = null,
    val imageURL: String = "",
    val genres: List<String> = listOf(),
    val directors: List<String> = listOf(),
    val writers: List<String> = listOf(),
    val stars: List<String> = listOf(),
)