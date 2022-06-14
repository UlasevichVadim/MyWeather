package com.vadimulasevich.yourweather.di

import android.content.Context
import com.vadimulasevich.yourweather.di.DependencyStorage.Api.apiWeather
import com.vadimulasevich.yourweather.mappers.ReqresWeatherApiToWeatherMapper
import com.vadimulasevich.yourweather.utils.PermissionChecker

object DependencyStorage {

    fun init(applicationContext: Context) {
        Android.init(applicationContext)
        //Database.init()
        //Dao.init()
        //Repositroes.init()

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

    //6. object DataBase{
    //lateinit bar appdatabase: Appdatabase
    //private set
    //fun init(){
    //appdatabase   = DependencyFactorie.createAppdaatabse(Android.applicationcontext)

    //3.Сюда добавим weatherDao object Dao{
    // lateinit bar weatherDao: WeatherDao
    //private set
    //fun init(){
    //weatherDao   = DependencyFactorie.createWeatherDao(DataBase.appdatabase)



    object Repositories {
        val networkWeatherRepository = DependencyFactories.createWeatherRepository(apiWeather)
        //2.Сюда добавим репозиторий для получения данных из базы
    // lateinit var weatrepostiro: WeatherRepository
        //private set
        //fun init (){
        // weatrepostiro = DependesiFactories.createWEatherRepositu(Dao.weatherDao, AppExecutors.ioexecutors, Faker.instance)
        // dependency.crearepository()

    }

    object Mappers {
        val reqresWeatherMapper = ReqresWeatherApiToWeatherMapper()
    }


    object Executors {
        val ioExecutor = DependencyFactories.createIoExecutor()
    }

}



