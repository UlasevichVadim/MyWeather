package com.vadimulasevich.myweather.ui.screen.search

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

class SearchScreenViewModel(
    private val weatherNetworkRepository: WeatherRepositoryNetwork,
    private val weatherMapper: ReqresWeatherApiToWeatherMapper
) : ViewModel() {

    private val _localWeatherWeekList = MutableLiveData<ResultState<List<Weather>>>()
    val localWeatherWeekList: LiveData<ResultState<List<Weather>>> = _localWeatherWeekList


    init {
        loadWeatherWeek()
    }

    private fun loadWeatherWeek() {
        Log.d("Search", "loadWeather")

        _localWeatherWeekList.value = ResultState.Loading()
        Log.d("Search", "loadWeather")

        weatherNetworkRepository.getWeatherAllNextWeek(object : Callback<ReqresWeatherApiResponse> {
            override fun onResponse(
                call: Call<ReqresWeatherApiResponse>,
                response: Response<ReqresWeatherApiResponse>,
            ) {
                Log.d("Search", "loadWeather")

                val responseBody = response.body()

                if (responseBody == null) {
                    _localWeatherWeekList.value =
                        ResultState.Error(RuntimeException("Response body is null"))
                    return
                }


                val weatherList = weatherMapper.toWeatherList(responseBody)
                _localWeatherWeekList.value = ResultState.Success(weatherList)
            }

            override fun onFailure(call: Call<ReqresWeatherApiResponse>, t: Throwable) {
                _localWeatherWeekList.value = ResultState.Error(t)
            }

        })
    }

    fun onUserClicked(weather: Weather) {

    }

}