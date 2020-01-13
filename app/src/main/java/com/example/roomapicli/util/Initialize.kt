package com.example.roomapicli.util

import android.app.Application
import com.example.roomapicli.di.appModule
import com.example.roomapicli.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Initialize : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Initialize)
            androidFileProperties()
            modules(listOf(appModule, repositoryModule))
        }
    }
}