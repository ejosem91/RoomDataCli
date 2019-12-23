package com.example.roomapicli.api

import com.example.roomapicli.model.Photo
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}