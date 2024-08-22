package com.example.aptoidedemo.core.managers

import com.example.aptoidedemo.core.models.local.ContentDao
import com.example.aptoidedemo.core.models.local.mapToContent
import com.example.aptoidedemo.core.models.local.mapToListContent
import com.example.aptoidedemo.core.models.local.mapToListContentEntity
import com.example.aptoidedemo.core.models.remote.mapToRoot
import com.example.aptoidedemo.core.models.result.ResultHandler
import com.example.aptoidedemo.core.network.services.AptoideService
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AptoideManager @Inject constructor(
    private val aptoideService: AptoideService,
    private val contentDao: ContentDao
) {

    suspend fun fetchData(): ResultHandler<Boolean> {
        return when (val result = aptoideService.fetchData()) {
            is ResultHandler.Error -> ResultHandler.Error(result.exception)
            is ResultHandler.Success -> {
                contentDao.insert(
                    result.data
                        .mapToRoot()
                        .responses.listApps.datasets.all.data.content
                        .mapToListContentEntity()
                )
                ResultHandler.Success(true)
            }
        }
    }

    fun observe() = contentDao.observe()
        .filterNotNull()
        .map { it.mapToListContent() }

    suspend fun getContent(id: Long) = contentDao.getContent(id).mapToContent()
}