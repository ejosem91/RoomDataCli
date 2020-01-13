package com.example.roomapicli.di

import com.example.core.repository.PhotoDataRepository
import com.example.core.usecase.PhotoUseCase
import com.example.data.api.Api
import com.example.data.database.PhotoDataBase
import com.example.data.repository.PhotoDataRepositoryImpl
import com.example.roomapicli.viewmodel.PhotoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { PhotoUseCase(get()) }

    viewModel { PhotoViewModel(userCase = get()) }
}

val repositoryModule = module {

    single { Api.retrofit() }

    single { PhotoDataBase.getInstance(androidContext()) }

    single { (get() as PhotoDataBase).photoDao() }

    single<PhotoDataRepository> { PhotoDataRepositoryImpl(get(), get()) }
}