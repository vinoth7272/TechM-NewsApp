package com.example.techmnewsapp

import android.app.Application
import com.example.techmnewsapp.di.databaseModule
import com.example.techmnewsapp.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TechMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TechMApp)
            androidLogger(Level.DEBUG)
            modules(arrayListOf(retrofitModule,databaseModule))
        }
    }
}