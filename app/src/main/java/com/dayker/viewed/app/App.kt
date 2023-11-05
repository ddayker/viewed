package com.dayker.viewed.app

import android.app.Application
import com.dayker.viewed.details.di.DetailsModules
import com.dayker.viewed.watched.di.WatchedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            val appModules = WatchedModules.modules + DetailsModules.modules
            modules(appModules)
        }
    }
}