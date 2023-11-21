package com.dayker.viewed.watched.common.platform.datasource.images

interface LocalImageDataSource {
    fun saveImage(uri: String, movieId: Long): String
    fun deleteImage(uri: String)
}