package com.example.aptoidedemo.core.network.endpoints

import com.example.aptoidedemo.core.models.remote.RootRemote
import retrofit2.Response
import retrofit2.http.GET

interface AptoideApi {

    companion object {
        private const val ENDPOINT = "/api/6/bulkRequest/api_list/listApps"
    }

    @GET(ENDPOINT)
    suspend fun getData(): Response<RootRemote>
}



