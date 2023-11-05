package com.dayker.viewed.watched.common.platform.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    val title: String,
    val releaseDate: String?,
    val duration: Long,
    val rating: Float,
    val review: String,
    val viewingDate: String?,
    val imageURL: String,
    val genres: String,
    val directors: String,
    val writers: String,
    val stars: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)
