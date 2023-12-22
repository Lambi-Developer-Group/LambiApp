package com.capstone.lambiapp.data.repository

import androidx.lifecycle.LiveData
import com.capstone.lambiapp.data.database.Photo
import com.capstone.lambiapp.data.database.PhotoDao

class PhotoRepository(private val photoDao: PhotoDao) {

    val allPhotos: LiveData<List<Photo>> = photoDao.getAllPhotos()

    suspend fun insert(photo: Photo) {
        photoDao.insertPhoto(photo)
    }
}