package com.example.aptoidedemo.core.workManager

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.aptoidedemo.R
import com.example.aptoidedemo.core.managers.AptoideManager
import com.example.aptoidedemo.core.workManager.ConstantUtils.CHANNEL_ID
import com.example.aptoidedemo.presentation.ui.MainActivity
import com.example.aptoidedemo.presentation.ui.NavigationRoute
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
                createNotification()
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }

    @SuppressLint("MissingPermission")
    private fun createNotification(
        title: String = appContext.getString(R.string.app_name),
        message: String = appContext.getString(R.string.notification_message)
    ) {
        val intent = Intent(appContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            appContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)  // Attach the PendingIntent
            .setAutoCancel(true)  // Remove notification after clicking
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
        return NotificationWorker(appContext, workerParameters, aptoideManager)
    }
}