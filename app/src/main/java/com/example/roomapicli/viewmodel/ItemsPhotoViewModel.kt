package com.example.roomapicli.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomapicli.model.Photo

class ItemsPhotoViewModel(var photo : Photo, var liveData : MutableLiveData<Photo>):ViewModel(){

    fun onClick(){
        liveData.value = photo
    }

}
