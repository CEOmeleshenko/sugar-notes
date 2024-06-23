package com.ceomeleshenko.sugarnotes.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UpdateCheckWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val notificationService = NotificationService(applicationContext)

    override fun doWork(): Result {
        checkForUpdates()
        return Result.success()
    }

    private fun checkForUpdates() {
        val currentTime = System.currentTimeMillis()
        getLastUpdateTime()

        if (lastUpdateTime != 0L && currentTime - lastUpdateTime >= 1 * 60 * 1000) {
            notificationService.showNotification()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getLastUpdateTime() {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val lastNote =
            DatabaseProvider.getDatabase(applicationContext).noteQueries.selectNotesByDate(date)
                .executeAsList().last()

        val time = SimpleDateFormat(
            "yyyy-MM-dd HH:mm",
            Locale.getDefault()
        ).parse(lastNote.date + " " + lastNote.time)?.time
            ?: 0L

        lastUpdateTime = time
    }

    companion object {
        var lastUpdateTime: Long = 0L
    }
}