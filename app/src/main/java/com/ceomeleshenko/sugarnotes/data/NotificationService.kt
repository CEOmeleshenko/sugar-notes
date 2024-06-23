package com.ceomeleshenko.sugarnotes.data

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.ceomeleshenko.sugarnotes.R
import kotlin.random.Random

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun showNotification() {
        val notification = NotificationCompat.Builder(context, "sugar_reminder")
            .setContentTitle("Напоминание об измерении сахара")
            .setContentText("Вы давно не делали записей показаний сахара крови!")
            .setSmallIcon(R.drawable.glucose)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}