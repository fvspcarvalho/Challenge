package com.example.aptoidedemo.core.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aptoidedemo.core.models.local.ContentEntity
import com.example.aptoidedemo.core.models.local.ContentDao

@Database(
    entities = [ContentEntity::class], version = 1
)

@TypeConverters(TypeConvertersUtil::class)
abstract class AptoideDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}