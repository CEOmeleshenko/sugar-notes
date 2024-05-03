package com.ceomeleshenko.sugarnotes

import android.app.Application
import com.ceomeleshenko.sugarnotes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SugarNotesApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SugarNotesApp)
            modules(appModule)
        }
    }
}