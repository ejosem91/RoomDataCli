package com.example.roomapicli.viewmodel


import androidx.lifecycle.*
import com.example.core.model.Photo
import com.example.core.usecase.PhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel(private val userCase: PhotoUseCase) : ViewModel() {

    init {
        getPhotoList()
    }

    var liveDataEvent: MutableLiveData<Photo> = MutableLiveData()
    private var photoListLiveDataSource: LiveData<MutableList<Photo>> = MutableLiveData()

    var photoListData = MediatorLiveData<List<Photo>>()

    private fun getPhotoList() = viewModelScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {
            photoListLiveDataSource = userCase.getPhotoData()
        }
        photoListData.value = photoListLiveDataSource.value
    }
}

class FactoryPhotoViewModel(private val useCasePhoto: PhotoUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(useCasePhoto) as T
    }
}