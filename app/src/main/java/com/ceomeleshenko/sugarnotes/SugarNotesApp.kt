package com.ceomeleshenko.sugarnotes

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ceomeleshenko.sugarnotes.data.UpdateCheckWorker
import com.ceomeleshenko.sugarnotes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class SugarNotesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SugarNotesApp)
            modules(appModule)
        }

        val notificationChannel = NotificationChannel(
            "sugar_reminder",
            "Sugar reminder channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.description = "SugarNotes notification channel"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)

        scheduleUpdateCheck(this)
    }

    private fun scheduleUpdateCheck(context: Context) {
        val updateCheckRequest = PeriodicWorkRequestBuilder<UpdateCheckWorker>(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(context).enqueue(updateCheckRequest)
    }
}