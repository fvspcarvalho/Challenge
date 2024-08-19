package com.example.aptoidedemo.data.manager

import com.example.aptoidedemo.data.local.Root
import com.example.aptoidedemo.data.network.mapToRoot
import com.example.aptoidedemo.data.service.AptoideService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AptoideManager @Inject constructor(
    private val aptoideService: AptoideService
) {
    suspend fun getData() = aptoideService.getData().body()?.mapToRoot() ?: Root()
}