package com.example.aptoidedemo.core.models.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
abstract class ContentDao {
    @Query("DELETE FROM content_table")
    abstract suspend fun clear()

    @Query("SELECT * FROM content_table ORDER BY updated DESC")
    abstract fun observe(): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content_table WHERE id LIKE :id")
    abstract suspend fun getContent(id: Long): ContentEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(contentEntities: List<ContentEntity>)
}