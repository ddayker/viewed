package com.dayker.viewed.watched.common.platform.datasource.images

interface ImageDataSource {
    fun saveImage(uri: String, movieId: Long): String
    fun deleteImage(uri: String)
}