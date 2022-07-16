package com.vadimulasevich.myweather.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vadimulasevich.myweather.BuildConfig
import com.vadimulasevich.myweather.db.local.WeatherDatabase
import com.vadimulasevich.myweather.db.local.dao.WeatherDao
import com.vadimulasevich.myweather.db.repositories.local.LocationRepository
import com.vadimulasevich.myweather.db.repositories.local.WeatherRepositoryDb
import com.vadimulasevich.myweather.db.repositories.network.WeatherRepositoryNetwork
import com.vadimulasevich.myweather.mappers.ReceivedWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.network.api.WeatherApi
import com.vadimulasevich.myweather.ui.screen.aboutapp.AboutAppScreenViewModel
import com.vadimulasevich.myweather.ui.screen.main.MainScreenViewModel
import com.vadimulasevich.myweather.ui.screen.search.SearchScreenViewModel
import com.vadimulasevich.myweather.utils.Constants
import com.vadimulasevich.myweather.utils.PermissionChecker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors


fun createLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return logging
}

fun createHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpClient.addInterceptor(logger)
    }
    return httpClient.build()
}

fun createRetrofit(client: OkHttpClient) = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .client(client)
    .build()

fun createApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)


fun createRepositoryNt(weatherApi: WeatherApi) = WeatherRepositoryNetwork(weatherApi)

fun createRepositoryDb(weatherDao: WeatherDao, ioExecutor: Executor) =
    WeatherRepositoryDb(weatherDao, ioExecutor)

fun createLocationRepository() = LocationRepository()

fun createMappers(): ReceivedWeatherApiToWeatherMapper {
    return ReceivedWeatherApiToWeatherMapper()
}

fun createIoExecutor(): Executor {
    return Executors.newFixedThreadPool(4)
}


fun createAppDatabase(context: Context): WeatherDatabase {
    return Room
        .databaseBuilder(context, WeatherDatabase::class.java, "db")
        .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
        .build()
}

fun createDao(appDatabase: WeatherDatabase): WeatherDao {
    return appDatabase.weatherDao()
}

val networkModule = module {
    single { createLoggingInterceptor() }
    single { createHttpClient(get()) }
    single { createRetrofit(get()) }
    single { createApi(get()) }
}

val mainModule = module {
    single { createRepositoryNt(get()) }
    single { createRepositoryDb(get(), get()) }
    single { createLocationRepository() }
    single { createMappers() }
    single { createIoExecutor() }

    viewModel { MainScreenViewModel(get(), get(), get(), get()) }
    viewModel { SearchScreenViewModel(get(), get()) }
    viewModel { AboutAppScreenViewModel() }
}

val dbModule = module {
    single { createAppDatabase(get()) }
    single { createDao(get()) }
}

val appModules = listOf(
    networkModule,
    dbModule,
    mainModule
)