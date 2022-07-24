package com.vadimulasevich.myweather.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.myweather.db.local.models.Weather
import com.vadimulasevich.myweather.db.repositories.local.LocationRepository
import com.vadimulasevich.myweather.db.repositories.local.WeatherRepositoryDb
import com.vadimulasevich.myweather.network.modelsOneDay.ReceivedWeatherApiResponse
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.mappers.ReceivedWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.db.repositories.network.WeatherRepositoryNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel(
    private val weatherNetworkRepository: WeatherRepositoryNetwork,
    private val weatherRepositoryDb: WeatherRepositoryDb,
    private val weatherMapper: ReceivedWeatherApiToWeatherMapper,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    private val _localWeatherList = MutableLiveData<ResultState<Weather>>()
    val localWeatherList: LiveData<ResultState<Weather>> = _localWeatherList

    fun loadWeather() {
        _localWeatherList.value = ResultState.Loading()
//        val lon = locationRepository.getLocation().longitude
//        val lat = locationRepository.getLocation().latitude
        val lon = 0.00
        val lat = 0.00

        weatherNetworkRepository.getWeather(
            lat,
            lon,
            object : Callback<ReceivedWeatherApiResponse> {
                override fun onResponse(
                    call: Call<ReceivedWeatherApiResponse>,
                    response: Response<ReceivedWeatherApiResponse>,
                ) {
                    val responseBody = response.body()
                    if (responseBody == null) {
                        val weather = weatherRepositoryDb.getWeather()
                        _localWeatherList.value = ResultState.Success(weather)
                    }
                    val weather = weatherMapper.toWeather(responseBody!!)
                    weatherRepositoryDb.addWeather(weather)
                    _localWeatherList.value = ResultState.Success(weather)
                }
                override fun onFailure(call: Call<ReceivedWeatherApiResponse>, t: Throwable) {
                    _localWeatherList.value = ResultState.Error(t)
                }
            })
    }
}