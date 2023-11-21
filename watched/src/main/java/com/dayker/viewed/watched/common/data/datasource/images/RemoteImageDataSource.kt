package com.dayker.viewed.watched.common.data.datasource.images

interface RemoteImageDataSource {
    suspend fun saveImage(uri: String, userId: String, movieId: Long): String?
    fun deleteImage(uri: String): Boolean
}