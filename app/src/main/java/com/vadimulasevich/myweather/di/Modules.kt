package com.vadimulasevich.myweather.di

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

val mainModule = module {
    single { DependencyFactories.createRepositoryNt(get()) }
    single { DependencyFactories.createRepositoryDb(get(), get()) }
    single { DependencyFactories.createMappers() }
    single { DependencyFactories.createIoExecutor() }
    single { DependencyFactories.createPermissionChecker(get()) }

    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { SearchScreenViewModel(get(), get()) }
    viewModel { AboutAppScreenViewModel() }
}

val dbModule = module {
    single { DependencyFactories.createAppDatabase(get()) }
    single { DependencyFactories.createDao(get()) }
}

val appModules = listOf(
    networkModule,
    dbModule,
    mainModule
)