package com.dayker.viewed.watchedmovies.data.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import androidx.core.net.toUri
import com.dayker.viewed.watchedmovies.domain.repository.ImageCachingRepository
import java.io.File

class ImageCachingRepositoryImpl(
    private val filesDir: File,
    private val contentResolver: ContentResolver
) : ImageCachingRepository {
    @SuppressLint("Recycle")
    override fun cacheImage(filePath: String): String {
        val input = contentResolver.openInputStream(filePath.toUri())
        val name = filePath.substringAfterLast("/") + ".jpg"
        val outputFile = filesDir.resolve(name)
        input?.copyTo(outputFile.outputStream())
        return outputFile.toUri().toString()
    }

    override fun deleteImage(fileName: String) {
        val file = File(filesDir, fileName)
        file.delete()
    }

    override fun changeSessionUriCacheNameToPermanent(
        sessionImageUri: String,
        permanentFileName: String
    ): String {
        val oldFile = File(filesDir, sessionImageUri.substringAfterLast("/"))
        val newFile = File(filesDir, permanentFileName)
        oldFile.renameTo(newFile)
        return newFile.toUri().toString()
    }
}