package com.example.roomapicli.repository

import androidx.lifecycle.MutableLiveData
import com.example.roomapicli.api.Api
import com.example.roomapicli.model.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryGetPhoto {

    private val client  = Api.retrofit()

    fun getPhotoData() : MutableLiveData<List<Photo>> {
        val liveData = MutableLiveData<List<Photo>>()
        client.getPhotos().enqueue(object :Callback<List<Photo>>{
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                liveData.value = response.body()
            }
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }
        })
        return  liveData
    }


}