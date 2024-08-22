package com.example.aptoidedemo.core.local.di

import android.content.Context
import androidx.room.Room
import com.example.aptoidedemo.core.local.database.AptoideDatabase
import com.example.aptoidedemo.core.models.local.ContentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProviders {

    @Provides
    @Singleton
    fun provideAptoideDatabase(@ApplicationContext context: Context): AptoideDatabase =
        Room.databaseBuilder(context, AptoideDatabase::class.java, "Aptoide-database").build()

    @Provides
    fun provideDataDao(database: AptoideDatabase): ContentDao = database.contentDao()
}