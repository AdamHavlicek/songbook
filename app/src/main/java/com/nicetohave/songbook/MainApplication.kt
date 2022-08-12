package com.nicetohave.songbook

import android.app.Application
import com.nicetohave.songbook.di.AndroidAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin Context
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(AndroidAppModule().module)
        }

    }
}