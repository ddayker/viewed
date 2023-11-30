package com.dayker.viewed.watched.common.data.datasource.images

import androidx.core.net.toUri
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

internal class FirebaseStorageDataSource(
    private val storage: StorageReference
) : RemoteImageDataSource {

    companion object {
        const val IMAGES_DIR = "images/"
        const val IMAGE_ID_FROM_IMAGE_URI_REGEX = """images%2F([a-zA-Z0-9]+)\?"""
    }

    override suspend fun saveImage(uri: String, userId: String, movieId: Long): String? {
        return try {
            var imageUri: String? = null
            val ref = storage.child("$IMAGES_DIR${userId + movieId}")
            val uploadTask = ref.putFile(uri.toUri())
            val imageUrlTask = uploadTask.continueWithTask {
                ref.downloadUrl
            }.await()
            imageUri = imageUrlTask.toString()
            return imageUri
        } catch (e: Exception) {
            println(e.message)
            null
        }
    }

    override fun deleteImage(uri: String): Boolean {
        try {
            val regex = Regex(IMAGE_ID_FROM_IMAGE_URI_REGEX)
            regex.find(uri)?.groups?.get(1)?.value?.let {
                val ref = storage.child(IMAGES_DIR + it)
                ref.delete()
                return true
            }
            return false
        } catch (e: Exception) {
            println(e.message)
            return false
        }
    }
}

