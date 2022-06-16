package com.vadimulasevich.myweather.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.vadimulasevich.myweather.db.local.WeatherDatabase
import com.vadimulasevich.myweather.mappers.ReceivedWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.ui.screen.aboutapp.AboutAppScreenViewModel
import com.vadimulasevich.myweather.ui.screen.main.MainScreenViewModel
import com.vadimulasevich.myweather.ui.screen.search.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    single { DependencyFactories.createLoggingInterceptor() }
    single { DependencyFactories.createHttpClient(get()) }
    single { DependencyFactories.createRetrofit(get()) }
    single { DependencyFactories.createApi(get()) }
}

val dbModule = module {
    single {
        Room
            .databaseBuilder(get(), WeatherDatabase::class.java, "db")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }
    single { get<WeatherDatabase>().weatherDao() }
}

val mainModule = module {
    single { DependencyFactories.createRepositoryNt(get()) }
    single { DependencyFactories.createRepositoryDb(get(), get()) }
    single { ReceivedWeatherApiToWeatherMapper() }
    single { DependencyFactories.createIoExecutor() }

    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { SearchScreenViewModel(get(), get()) }
    viewModel { AboutAppScreenViewModel() }
}

val appModules = listOf(
    networkModule,
    dbModule,
    mainModule
)