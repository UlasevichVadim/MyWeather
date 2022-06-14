package com.vadimulasevich.yourweather.di

import android.content.Context
import com.vadimulasevich.yourweather.BuildConfig
import com.vadimulasevich.yourweather.network.WeatherApi
import com.vadimulasevich.yourweather.repository.WeatherNetworkRepository
import com.vadimulasevich.yourweather.utils.PermissionChecker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object DependencyFactories {

    fun createWeatherRepository(weatherApi: WeatherApi) = WeatherNetworkRepository(weatherApi)

    //1.Сюда добавим репозиторий для получения данных из базы createrepostiro(userDAo, ioexecutor, faker) = repository(userdao, ioexecitr, faker)

    //4.Сюда добавим weatherDao для получения данных из базы createDao(appDataBase): WeatherDao {
    // return appdatabase.weatherDao()

    //5. Сюда добавим создание датаБазы createAppDatabase(context) {
    //return Room .databaseBuilder .....

    fun createApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    fun createRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(WeatherApi.BASE_URL)
        .client(client)
        .build()

    fun createHttpClient(logger: HttpLoggingInterceptor) : OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if(BuildConfig.DEBUG){
            httpClient.addInterceptor(logger)
        }
        return httpClient.build()
    }

    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    fun createIoExecutor(): ExecutorService? {
        return Executors.newFixedThreadPool(4)
    }

    fun createPermissionChecker(applicationContext: Context): PermissionChecker {
        return PermissionChecker(applicationContext)
    }
}