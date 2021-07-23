package com.example.iphonesearchapi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.iphonesearchapi.model.Result
import kotlinx.coroutines.flow.Flow


@Dao
interface SongDatabaseDao {
    @Insert
    suspend fun insert(trackId: SongDatabase)

    @Delete
    suspend fun delete(trackId: SongDatabase)

    @Query("Select * from a ")
    fun select(): Flow<List<Long>>

}