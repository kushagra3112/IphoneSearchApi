package com.example.iphonesearchapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "a")
data class SongDatabase(
    @PrimaryKey(autoGenerate = false)
    var TrackId: Long = 0L
)