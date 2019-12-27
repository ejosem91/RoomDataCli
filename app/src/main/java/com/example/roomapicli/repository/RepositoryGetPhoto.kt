package com.example.roomapicli.repository

import androidx.lifecycle.MutableLiveData
import com.example.roomapicli.api.Api
import com.example.roomapicli.database.PhotoDataBase
import com.example.roomapicli.model.Photo

class RepositoryGetPhoto(private val dataBase: PhotoDataBase?) {

    private val client = Api.retrofit()
    suspend fun getPhotoDataAPI(): MutableLiveData<List<Photo>> {
        val liveData = MutableLiveData<List<Photo>>()
        val listPhoto = client.getPhotosAsync().await()
        val cutList = listPhoto.subList(0, 24)

        cutList.forEach {
            dataBase?.photoDao()?.insert(it)
        }
        liveData.postValue(dataBase?.photoDao()?.getAll())
        return liveData
    }

}
