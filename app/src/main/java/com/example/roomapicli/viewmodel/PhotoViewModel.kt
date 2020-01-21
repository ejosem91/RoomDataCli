package com.example.roomapicli.viewmodel


import androidx.lifecycle.*
import com.example.core.model.Photo
import com.example.core.usecase.PhotoUseCase
import com.example.roomapicli.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel(private val userCase: PhotoUseCase) : ViewModel() {
    
    var liveDataEvent: MutableLiveData<Event<Photo>> = MutableLiveData()

    private var photoListLiveDataSource: LiveData<MutableList<Photo>> = MutableLiveData()

    var photoListData = MediatorLiveData<List<Photo>>()

    fun getPhotoList() = viewModelScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {
            photoListLiveDataSource = userCase.getPhotoData()
        }
        photoListData.value = photoListLiveDataSource.value
    }
}

