package com.vadimulasevich.myweather.ui.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.myweather.db.local.models.Weather
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.mappers.ReqresWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.db.network.models.ReqresWeatherApiResponse
import com.vadimulasevich.myweather.db.repositories.network.WeatherRepositoryNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel(
    private val weatherNetworkRepository: WeatherRepositoryNetwork,
//    private val weatherRepositoryDb: WeatherRepositoryDb,
    private val weatherMapper: ReqresWeatherApiToWeatherMapper
) : ViewModel(){

    private val _localWeatherList = MutableLiveData<ResultState<List<Weather>>>()
    val localWeatherList: LiveData<ResultState<List<Weather>>> = _localWeatherList


    init {
         loadWeather()
    }

    private fun loadWeather() {
        Log.d("Main", "loadWeather")
        _localWeatherList.value = ResultState.Loading()
        Log.d("Main", "loadWeather next")
        weatherNetworkRepository.getWeatherOneTownOneDay(object : Callback<ReqresWeatherApiResponse> {
            override fun onResponse(
                call: Call<ReqresWeatherApiResponse>,
                response: Response<ReqresWeatherApiResponse>
            ) {
                Log.d("Main", "loadWeather onResponse")
                val responseBody = response.body()

                if (responseBody == null) {
                    Log.d("Main", "null onResponse")
                    _localWeatherList.value =
                        ResultState.Error(RuntimeException("Response body is null"))
                    return
                }

                Log.d("Main", "not null onResponse")

                val weatherList = weatherMapper.toWeatherList(responseBody)
                _localWeatherList.value = ResultState.Success(weatherList)
            }

            override fun onFailure(call: Call<ReqresWeatherApiResponse>, t: Throwable) {
                _localWeatherList.value = ResultState.Error(t)
            }

        })

    }
}