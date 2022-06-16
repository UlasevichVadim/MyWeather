package com.vadimulasevich.myweather.di

import android.content.Context
import com.vadimulasevich.myweather.db.local.WeatherDatabase
import com.vadimulasevich.myweather.db.local.dao.WeatherDao
import com.vadimulasevich.myweather.di.DependencyStorage.Api.apiWeather
import com.vadimulasevich.myweather.mappers.ReceivedWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.db.repositories.local.WeatherRepositoryDb
import com.vadimulasevich.myweather.utils.PermissionChecker

object DependencyStorage {

    fun init(applicationContext: Context) {
        Android.init(applicationContext)
        DataBase.init()
        Dao.init()
        Repositories.init()

    }


    object Android {
        lateinit var applicationContext: Context
            private set

        lateinit var permissionChecker: PermissionChecker
            private set

        fun init(applicationContext: Context) {
            Android.applicationContext = applicationContext
            permissionChecker = DependencyFactories.createPermissionChecker(applicationContext)
        }
    }


    object Network {
        val loggingInterceptor = DependencyFactories.createLoggingInterceptor()
        val httpClient = DependencyFactories.createHttpClient(loggingInterceptor)
        val retrofit = DependencyFactories.createRetrofit(httpClient)
    }

    object Api {
        val apiWeather = DependencyFactories.createApi(Network.retrofit)
    }

    object DataBase {
        lateinit var appdatabase: WeatherDatabase
            private set

        fun init() {
            appdatabase = DependencyFactories.createAppDatabase(Android.applicationContext)

        }
    }

    object Dao {
        lateinit var weatherDao: WeatherDao
            private set

        fun init() {
            weatherDao = DependencyFactories.createDao(DataBase.appdatabase)


        }
    }

    object Repositories {
        val networkWeatherRepository = DependencyFactories.createRepositoryNt(apiWeather)
        lateinit var repositoryDb: WeatherRepositoryDb
            private set

        fun init() {
            repositoryDb =
                DependencyFactories.createRepositoryDb(Dao.weatherDao, Executors.ioExecutor)
        }
    }

    object Mappers {
        val reqresWeatherMapper = ReceivedWeatherApiToWeatherMapper()
    }


    object Executors {
        val ioExecutor = DependencyFactories.createIoExecutor()
    }

}



