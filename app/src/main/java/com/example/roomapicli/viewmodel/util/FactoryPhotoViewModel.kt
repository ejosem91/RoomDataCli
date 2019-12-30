package com.example.roomapicli.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapicli.repository.RepositoryGetPhoto
import com.example.roomapicli.viewmodel.PhotoViewModel

class FactoryPhotoViewModel(private val photoDao: RepositoryGetPhoto) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(photoDao) as T
    }
}