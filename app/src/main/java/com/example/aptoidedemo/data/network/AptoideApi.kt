package com.example.aptoidedemo.data.network

import com.example.aptoidedemo.data.service.AptoideService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface AptoideApi {
    companion object {
        private const val ENDPOINT = "/api/6/bulkRequest/api_list/listApps"
    }

    @GET(ENDPOINT)
    suspend fun getData(): Response<RootRemote>
}



