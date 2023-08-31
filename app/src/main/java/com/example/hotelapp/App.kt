package com.example.hotelapp

import android.app.Application
import com.example.hotelapp.di.appModule
import com.example.hotelapp.di.dataModule
import com.example.hotelapp.di.navigationModule
import com.example.hotelapp.di.networkModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            modules(listOf(appModule, navigationModule, dataModule, networkModule))
        }
    }
}