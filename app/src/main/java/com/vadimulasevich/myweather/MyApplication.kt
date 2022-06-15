package com.vadimulasevich.myweather

import android.app.Application
import com.vadimulasevich.myweather.di.DependencyStorage
import com.vadimulasevich.myweather.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyStorage.init(this)

        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
        }
    }
}