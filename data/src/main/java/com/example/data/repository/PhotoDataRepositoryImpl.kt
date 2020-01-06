package com.example.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.core.model.Photo
import com.example.core.repository.PhotoDataRepository
import com.example.data.api.Api
import com.example.data.database.PhotoDataBase

class PhotoDataRepositoryImpl(api: Api, private val dataBase: PhotoDataBase) : PhotoDataRepository {

    private val client = api.retrofit()

    override suspend fun getDataPhoto(): MutableLiveData<MutableList<Photo>> {
        val liveData = MutableLiveData<MutableList<Photo>>()

        val listPhoto = client.getPhotosAsync().await()

        val getList = listPhoto.subList(0, 24)
        getList.forEach {
            dataBase.photoDao().insert(it.mapToRoomModel())
        }
        val listItems: MutableList<Photo> = mutableListOf()
        dataBase.photoDao().getAll().forEach {
            listItems.add(it.mapToDomainModel())
        }
        liveData.postValue(listItems)

        return liveData
    }
}