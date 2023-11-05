package com.dayker.viewed.watchedmovies.common.platform.datasource.images

interface ImageDataSource {
    fun saveImage(uri: String, movieId: Long): String
    fun deleteImage(uri: String)
}