package com.vadimulasevich.yourweather.ui.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.yourweather.ResultState
import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.network.modelsNetwork.WeatherApiResponse
import com.vadimulasevich.yourweather.repository.WeatherNetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreenViewModel(
    private val weatherNetworkRepository: WeatherNetworkRepository,
) : ViewModel() {

    private val _localWeatherList = MutableLiveData<ResultState<List<Weather>>>()
    val localWeatherList: LiveData<ResultState<List<Weather>>> = _localWeatherList


    init {
       loadWeather()
    }

    private fun loadWeather() {
        _localWeatherList.value = ResultState.Loading()
        weatherNetworkRepository.getWeather(object : Callback<WeatherApiResponse> {
            override fun onResponse(call: Call<WeatherApiResponse>, response: Response<WeatherApiResponse>) {
                 val result = response.body()?.let{ responseBody ->
                     val weatherList = responseBody.weather.map {
                         Weather(
                             id = it.id,
                             tempreture = it.main,
                             description = it.description,
                             address = it.icon
                         )
                     }
                     ResultState.Success(weatherList)
                 } ?: ResultState.Error(RuntimeException("Response body is null"))

                _localWeatherList.value = result
            }

            override fun onFailure(call: Call<WeatherApiResponse>, t: Throwable) {
                _localWeatherList.value = ResultState.Error(t)
            }

        })
    }

    fun onUserClicked(weather: Weather) {

    }

}