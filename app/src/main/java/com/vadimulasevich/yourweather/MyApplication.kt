package com.vadimulasevich.yourweather

import android.app.Application
import com.vadimulasevich.yourweather.db.models.AppDatabase
import com.vadimulasevich.yourweather.di.DependencyStorage
import com.vadimulasevich.yourweather.di.appModules
import com.vadimulasevich.yourweather.di.networkModule
import com.vadimulasevich.yourweather.repository.WeatherRepositoryDb
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyStorage.init(this)
        AppDatabase.init(this)

        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
        }
    }
}