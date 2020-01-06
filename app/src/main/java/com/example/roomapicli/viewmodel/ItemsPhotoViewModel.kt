package com.example.roomapicli.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.Photo

class ItemsPhotoViewModel(var photo : Photo, private  var liveData : MutableLiveData<Photo>):ViewModel(){

    fun onClick(){
        liveData.value = photo
    }

}
