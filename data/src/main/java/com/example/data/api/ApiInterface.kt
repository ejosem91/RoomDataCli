package com.example.data.api

import com.example.data.model.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getPhotosAsync(): Deferred<List<Photo>>
}