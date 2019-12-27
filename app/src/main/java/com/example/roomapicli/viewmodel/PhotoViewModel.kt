package com.example.roomapicli.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.roomapicli.model.Photo
import com.example.roomapicli.repository.RepositoryGetPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel(private val repositoryGetPhoto: RepositoryGetPhoto) : ViewModel() {


    init{
        getPhotoList()
    }

//    fun getListPhoto(): MutableLiveData<List<Photo>> {
//        return repositoryGetPhoto.getPhotoData()
//    }

    var liveData: MutableLiveData<Photo> = MutableLiveData()
    var photoListLiveDataSource: MutableLiveData<List<Photo>> = MutableLiveData()

    var photoListData = MediatorLiveData<List<Photo>>()

    private fun getPhotoList() = viewModelScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) {
            photoListLiveDataSource = repositoryGetPhoto.getPhotoDataAPI()
        }
        photoListData.value = photoListLiveDataSource.value
    }
}

class FactoryPhotoViewModel(private val photoDao: RepositoryGetPhoto) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotoViewModel(photoDao) as T
    }
}