package com.example.iphonesearchapi.network

import android.app.Application
import com.example.iphonesearchapi.database.Database1
import com.example.iphonesearchapi.database.SongRepository
import com.example.iphonesearchapi.di.ItunesModule
import com.example.iphonesearchapi.di.RetrofitModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
class CustomApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { Database1.getDatabase(this, applicationScope) }
    val repository by lazy { SongRepository(database.SongDatabaseDao()) }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CustomApp)
            modules(RetrofitModule, ItunesModule)
        }

    }
}