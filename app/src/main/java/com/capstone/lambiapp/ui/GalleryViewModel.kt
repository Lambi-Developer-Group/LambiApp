package com.capstone.lambiapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.capstone.lambiapp.data.database.AppDatabase
import com.capstone.lambiapp.data.database.Photo
import com.capstone.lambiapp.data.database.PhotoDao
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val photoDao: PhotoDao
    val allPhotos: LiveData<List<Photo>>

    init {
        val database = AppDatabase.getDatabase(application)
        photoDao = database.photoDao()
        allPhotos = photoDao.getAllPhotos()
    }

    fun deletePhoto(photo: Photo) {
        viewModelScope.launch {
            photoDao.deletePhoto(photo)
        }

    }
}
