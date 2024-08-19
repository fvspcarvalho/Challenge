package com.example.aptoidedemo.data.service

import com.example.aptoidedemo.data.network.AptoideApi
import com.example.aptoidedemo.data.network.RootRemote
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AptoideService @Inject constructor() {
    val json = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ws2.aptoide.com")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val apiAptoide = retrofit.create(AptoideApi::class.java)

    suspend fun getData(): Response<RootRemote> = apiAptoide.getData()
}
