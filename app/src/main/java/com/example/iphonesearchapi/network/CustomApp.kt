package com.example.iphonesearchapi.network

import android.app.Application
import com.example.iphonesearchapi.di.ItunesModule
import com.example.iphonesearchapi.di.RetrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CustomApp)
            modules(RetrofitModule, ItunesModule)
        }
    }
}