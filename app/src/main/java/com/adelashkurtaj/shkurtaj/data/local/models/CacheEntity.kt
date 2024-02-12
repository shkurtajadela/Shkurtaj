package com.adelashkurtaj.shkurtaj.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adelashkurtaj.shkurtaj.data.local.AppDatabase

@Entity(tableName = AppDatabase.CACHE_TABLE_NAME)
data class CacheEntity(
    @PrimaryKey
    val apiRequest: String,
    val response: String
)