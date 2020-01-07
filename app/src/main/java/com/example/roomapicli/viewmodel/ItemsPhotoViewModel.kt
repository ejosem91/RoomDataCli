package com.example.roomapicli.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.Photo
import com.example.roomapicli.util.Event

class ItemsPhotoViewModel(var photo : Photo, private  var photoEvent : MutableLiveData<Event<Photo>>):ViewModel(){

    fun onClick(){
        photoEvent.value = Event(photo)
    }

}
