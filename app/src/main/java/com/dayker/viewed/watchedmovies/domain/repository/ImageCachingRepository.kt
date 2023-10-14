package com.dayker.viewed.watchedmovies.domain.repository

interface ImageCachingRepository {
    fun cacheImage(filePath: String): String
    fun deleteImage(fileName: String)
    fun changeSessionUriCacheNameToPermanent(
        sessionImageUri: String,
        permanentFileName: String
    ): String

}