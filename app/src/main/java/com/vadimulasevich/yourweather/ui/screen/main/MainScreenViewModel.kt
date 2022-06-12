package com.vadimulasevich.yourweather.ui.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.yourweather.ResultState
import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.mappers.ReqresWeatherApiToWeatherMapper
import com.vadimulasevich.yourweather.network.modelsNetwork.ReqresWeatherApiResponse
import com.vadimulasevich.yourweather.repository.WeatherNetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel(
    private val weatherNetworkRepository: WeatherNetworkRepository,
    private val weatherMapper: ReqresWeatherApiToWeatherMapper
) : ViewModel(){

    private val _localWeatherList = MutableLiveData<ResultState<List<Weather>>>()
    val localWeatherList: LiveData<ResultState<List<Weather>>> = _localWeatherList


    init {
        loadWeather()
    }

    private fun loadWeather() {
        _localWeatherList.value = ResultState.Loading()
        weatherNetworkRepository.getWeatherOneTownOneDay(object : Callback<ReqresWeatherApiResponse> {
            override fun onResponse(
                call: Call<ReqresWeatherApiResponse>,
                response: Response<ReqresWeatherApiResponse>
            ) {
                val responseBody = response.body()

                if (responseBody == null) {
                    _localWeatherList.value =
                        ResultState.Error(RuntimeException("Response body is null"))
                    return
                }
                val weatherList = weatherMapper.toWeatherList(responseBody)
                _localWeatherList.value = ResultState.Success(weatherList)
            }

            override fun onFailure(call: Call<ReqresWeatherApiResponse>, t: Throwable) {
                _localWeatherList.value = ResultState.Error(t)
            }

        })

    }
}