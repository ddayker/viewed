package com.dayker.viewed.watchedmovies.data.repository.utils

object CacheUtils {

    fun getIdImageFileName(id: Long?): String {
        return "movie$id.jpg"
    }
}