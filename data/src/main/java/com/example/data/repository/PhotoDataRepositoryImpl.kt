package com.example.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.core.model.Photo
import com.example.core.repository.PhotoDataRepository
import com.example.data.api.ApiInterface
import com.example.data.database.PhotoDao
import com.example.data.model.PhotoEntity

class PhotoDataRepositoryImpl(private val api: ApiInterface, private val dataBaseDao: PhotoDao) : PhotoDataRepository {

    override suspend fun getDataPhoto(): MutableLiveData<MutableList<Photo>> {
        val liveData = MutableLiveData<MutableList<Photo>>()
        val listPhoto = api.getPhotosAsync().await()
        val getList = listPhoto.subList(0, 24)
        val listItem :MutableList<PhotoEntity> = mutableListOf()

        getList.forEach {
            listItem.add(it.mapToRoomModel())
        }
        dataBaseDao.insert(listItem)

        val listItems: MutableList<Photo> = mutableListOf()

        dataBaseDao.getAll().forEach {
            listItems.add(it.mapToDomainModel())
        }
        liveData.postValue(listItems)

        return liveData
    }
}