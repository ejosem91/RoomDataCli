package com.example.roomapicli.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomapicli.model.Photo
import com.example.roomapicli.repository.RepositoryGetPhoto

class PhotoModelView: ViewModel() {

    var dataList : RepositoryGetPhoto = RepositoryGetPhoto()

    fun getListPhoto(): MutableLiveData<List<Photo>> {
        return dataList.getPhotoData()
    }

}