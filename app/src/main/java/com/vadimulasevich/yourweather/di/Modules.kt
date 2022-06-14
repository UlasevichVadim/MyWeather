package com.vadimulasevich.yourweather.di

import com.vadimulasevich.yourweather.mappers.ReqresWeatherApiToWeatherMapper
import org.koin.dsl.module


val networkModule = module {
    single { DependencyFactories.createLoggingInterceptor() }
    single { DependencyFactories.createHttpClient(get()) }
    single { DependencyFactories.createRetrofit(get()) }
    single { DependencyFactories.createApi(get()) }
}
//
//val dbModule = module {
//    single {
//        Room
//            .databaseBuilder(get(), AppDatabase::class.java, "db")
//            // чтобы база была в одном файлике (без  Write-Ahead-Log)
//            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
//            .build()
//    }
//    single { get<AppDatabase>().userDao() }
//}
//
val mainModule = module {
//    single { Faker.instance() }
    single { DependencyFactories.createWeatherRepository(get()) }
//    single { DependencyFactories.createUserRepository(get(), get(), get()) }  DB нужен
    single { ReqresWeatherApiToWeatherMapper() }
    single { DependencyFactories.createIoExecutor() }

//    viewModel { UserListViewModel(get()) }
}
//
val appModules = listOf(
    networkModule,
//    dbModule,
    mainModule
)