package com.nicetohave.songbook

import android.app.Application
import android.content.Context
import com.nicetohave.songbook.core.modules.AndroidAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
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
            androidFileProperties()
            modules(AndroidAppModule().module)
        }

    }
}
