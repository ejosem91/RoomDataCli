package com.example.roomapicli.viewmodel


import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapicli.model.Photo
import com.example.roomapicli.repository.RepositoryGetPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel(private val repositoryGetPhoto: RepositoryGetPhoto) : ViewModel() {

    init {
        getPhotoList()
    }

    var liveDataEvent: MutableLiveData<Photo> = MutableLiveData()
    private var photoListLiveDataSource: MutableLiveData<List<Photo>> = MutableLiveData()

    var photoListData = MediatorLiveData<List<Photo>>()

    private fun getPhotoList() = viewModelScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {
            photoListLiveDataSource = repositoryGetPhoto.getPhotoDataAPI()
        }
        photoListData.value = photoListLiveDataSource.value
    }
}