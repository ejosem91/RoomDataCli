package com.example.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.core.model.Photo
import com.example.core.repository.PhotoDataRepository
import com.example.data.api.Api
import com.example.data.database.PhotoDataBase

class PhotoDataRepositoryImpl( api: Api , private val dataBase : PhotoDataBase):PhotoDataRepository {


    private val client = api.retrofit()

    override suspend fun getDataPhoto(): MutableLiveData<List<Photo>> {
        val liveData = MutableLiveData<List<Photo>>()
        val listPhoto = client.getPhotosAsync().await()

        val getList = listPhoto.subList(0,24)


        getList.forEach {
            dataBase?.photoDao()?.insert(it)
        }

        return  liveData.postValue(dataBase?.photoDao()?.getAll())
    }
}