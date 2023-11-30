package com.dayker.viewed.watched.common.platform.datasource.images

import android.annotation.SuppressLint
import android.content.ContentResolver
import androidx.core.net.toUri
import java.io.File

internal class AndroidImageDataSource(
    private val filesDir: File,
    private val contentResolver: ContentResolver
) : LocalImageDataSource {

    companion object {
        const val JPG = ".jpg"
        const val DEFAULT_NAME = "movie"
    }

    @SuppressLint("Recycle")
    override fun saveImage(uri: String, movieId: Long): String {
        val input = contentResolver.openInputStream(uri.toUri())
        val name = "$DEFAULT_NAME$movieId$JPG"
        val outputFile = filesDir.resolve(name)
        if (outputFile.toUri().toString() != uri) {
            input?.copyTo(outputFile.outputStream())
        }
        return outputFile.toUri().toString()
    }

    override fun deleteImage(uri: String) {
        val file = File(filesDir, uri.substringAfterLast("/"))
        file.delete()
    }
}