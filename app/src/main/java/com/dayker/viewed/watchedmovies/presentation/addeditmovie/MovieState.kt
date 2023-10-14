package com.dayker.viewed.watchedmovies.presentation.addeditmovie

data class MovieState(
    val title: String = "",
    val releaseDate: Long? = null,
    val duration: Long = 0,
    val rating: Float = 7.8f,
    val review: String = "",
    val viewingDate: Long? = null,
    val imageURL: String? = null,
    val isImageChanged: Boolean = false,
    val genres: List<String> = listOf(),
    val directors: List<String> = listOf(),
    val writers: List<String> = listOf(),
    val stars: List<String> = listOf()
)

