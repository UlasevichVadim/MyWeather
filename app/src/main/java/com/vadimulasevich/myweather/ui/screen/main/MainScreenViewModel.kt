package com.vadimulasevich.myweather.ui.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.myweather.db.local.models.Weather
import com.vadimulasevich.myweather.network.modelsOneDay.ReceivedWeatherApiResponse
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.mappers.ReceivedWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.db.repositories.network.WeatherRepositoryNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel(
    private val weatherNetworkRepository: WeatherRepositoryNetwork,
//    private val weatherRepositoryDb: WeatherRepositoryDb,
    private val weatherMapper: ReceivedWeatherApiToWeatherMapper,
) : ViewModel() {

    private val _localWeatherList = MutableLiveData<ResultState<Weather>>()
    val localWeatherList: LiveData<ResultState<Weather>> = _localWeatherList

    init {
        Log.d("getWeather", "init MainScreenViewModel")
        loadWeather()
    }

    private fun loadWeather() {
        _localWeatherList.value = ResultState.Loading()

        val lon = 27.5667
        val lat = 53.9
        //Нужно получить геоданные

        weatherNetworkRepository.getWeather(
            lat,
            lon,
            object : Callback<ReceivedWeatherApiResponse> {
                override fun onResponse(
                    call: Call<ReceivedWeatherApiResponse>,
                    response: Response<ReceivedWeatherApiResponse>,
                ) {
                    Log.d("getWeather", "In")
                    val responseBody = response.body()

                    if (responseBody == null) {
                        _localWeatherList.value =
                            ResultState.Error(RuntimeException("Response body is null"))
                        return
                    }

                    val weather = weatherMapper.toWeather(responseBody)
                    _localWeatherList.value = ResultState.Success(weather)
                }

                override fun onFailure(call: Call<ReceivedWeatherApiResponse>, t: Throwable) {
                    _localWeatherList.value = ResultState.Error(t)
                }
            })
    }
}