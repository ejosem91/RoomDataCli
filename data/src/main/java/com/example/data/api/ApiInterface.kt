package com.example.roomapicli.api

import com.example.roomapicli.model.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getPhotosAsync(): Deferred<List<Photo>>
}