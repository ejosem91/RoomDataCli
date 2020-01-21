package com.example.roomapicli


import androidx.lifecycle.MutableLiveData
import com.example.core.model.Photo
import com.example.core.usecase.PhotoUseCase
import com.example.data.repository.PhotoDataRepositoryImpl
import com.example.roomapicli.viewmodel.PhotoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class PhotoViewModelTest {

    private lateinit var useCase: PhotoUseCase
    private lateinit var viewModelPhoto: PhotoViewModel
    private lateinit var photoDataRepository: PhotoDataRepositoryImpl
    @ExperimentalCoroutinesApi
    private var dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        runBlocking {
            val mutableLiveData = MutableLiveData<MutableList<Photo>>()
            mutableLiveData.value?.addAll(
                mutableListOf(
                    Photo(
                        1,
                        1,
                        "Photo Title",
                        "https://www.google.com",
                        "https://www.google.com"
                    )
                )
            )

            photoDataRepository = mock(PhotoDataRepositoryImpl::class.java)

            `when`(photoDataRepository.getDataPhoto()).thenReturn(mutableLiveData)

            useCase = PhotoUseCase(photoDataRepository)

            viewModelPhoto = PhotoViewModel(useCase)
        }
    }


    @Test
    fun `when viewModel's getPhotoList() is invoked, Repository is queried`() {
        runBlocking {
            viewModelPhoto.getPhotoList()
            verify(photoDataRepository).getDataPhoto()
        }
    }

}