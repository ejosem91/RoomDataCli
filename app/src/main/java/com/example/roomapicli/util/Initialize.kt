package com.example.roomapicli.util

import android.app.Application
import com.example.core.usecase.PhotoUseCase
import com.example.data.api.Api
import com.example.data.database.PhotoDataBase
import com.example.data.repository.PhotoDataRepositoryImpl

class Initialize : Application() {

    lateinit var useCase: PhotoUseCase
    private lateinit var dataBase: PhotoDataBase

    override fun onCreate() {
        super.onCreate()
        dataBase = PhotoDataBase.getInstance(context = applicationContext)
        val api = Api
        val repository = PhotoDataRepositoryImpl(api, dataBase)
        useCase = PhotoUseCase(repository)
    }
}