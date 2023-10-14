package com.dayker.viewed.watchedmovies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    val title: String,
    val releaseDate: Long?,
    val duration: Long,
    val rating: Float,
    val review: String,
    val viewingDate: Long?,
    val imageURL: String,
    val genres: String,
    val directors: String,
    val writers: String,
    val stars: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)