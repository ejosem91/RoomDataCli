package com.example.roomapicli.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.roomapicli.api.Api
import com.example.roomapicli.database.PhotoDataBase
import com.example.roomapicli.model.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryGetPhoto(val dataBase: PhotoDataBase?) {

    private val client  = Api.retrofit()



//    fun getPhotoData() : MutableLiveData<List<Photo>> {
//        val liveData = MutableLiveData<List<Photo>>()
//        client.getPhotos().enqueue(object :Callback<List<Photo>>{
//            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
//                liveData.value = response.body()
//                liveData.value = liveData.value?.subList(0,24)
//            }
//            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
//
//            }
//        })
//        return  liveData
//    }



    suspend fun getPhotoDataAPI() : MutableLiveData<List<Photo>> {
        var  liveData  = MutableLiveData<List<Photo>>()
        val listPhoto =  client.getPhotosAsync().await()
        var cutList =listPhoto.subList(0,24)

        cutList.forEach {
            dataBase?.photoDao()?.insert(it)
        }
        liveData.postValue(dataBase?.photoDao()?.getAll()) //as MutableLiveData<List<Photo>>
        return  liveData
        }

    }
