package com.example.aptoidedemo.core.network.services

import com.example.aptoidedemo.core.models.remote.RootRemote
import com.example.aptoidedemo.core.models.result.ResultHandler
import com.example.aptoidedemo.core.network.endpoints.AptoideApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AptoideService @Inject constructor(private val retrofit: Retrofit) {

    private val apiAptoide = retrofit.create(AptoideApi::class.java)

    suspend fun fetchData(): ResultHandler<RootRemote> {
        return try {
            val response = apiAptoide.getData()
            if (response.isSuccessful) {
                val data = response.body()
                if (data == null) {
                    ResultHandler.Error(Exception("Error: ${response.errorBody()}"))
                } else {
                    ResultHandler.Success(data)
                }

            } else {
                ResultHandler.Error(Exception("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            ResultHandler.Error(e)
        }
    }
}