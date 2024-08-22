package com.example.aptoidedemo.core.workManager

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.aptoidedemo.R
import com.example.aptoidedemo.core.managers.AptoideManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val aptoideManager: AptoideManager
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        try {
            withContext(Dispatchers.IO) {
                aptoideManager.fetchData()
                createNotification("Aptoide Demo", "You have new apps available!")
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    @SuppressLint("MissingPermission")
    private fun createNotification(title: String, message: String) {

        val notification = NotificationCompat.Builder(appContext, "work_manager_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(appContext).notify(1, notification)

    }
}

class MyWorkerFactory @Inject constructor(private val aptoideManager: AptoideManager) :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        // This only handles a single Worker, please don’t do this!!
        // See below for a better way using DelegatingWorkerFactory
        return NotificationWorker(appContext, workerParameters, aptoideManager)
    }
}