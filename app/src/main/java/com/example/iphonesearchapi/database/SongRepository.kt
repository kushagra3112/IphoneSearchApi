package com.example.iphonesearchapi.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class SongRepository(private val songDatabaseDao: SongDatabaseDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    @WorkerThread
    fun observe() = songDatabaseDao.select()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(trackId: Long) = songDatabaseDao.insert(SongDatabase(trackId))

    @WorkerThread
    suspend fun delete(trackId: Long) = songDatabaseDao.delete(SongDatabase(trackId))

}
