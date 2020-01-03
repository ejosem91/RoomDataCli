package com.example.core.repository

import androidx.lifecycle.LiveData
import com.example.core.model.Photo


interface PhotoDataRepository {
    suspend fun getDataPhoto(): LiveData<List<Photo>>
}