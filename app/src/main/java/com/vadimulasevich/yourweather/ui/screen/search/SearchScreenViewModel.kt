package com.vadimulasevich.yourweather.ui.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.yourweather.ResultState
import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.mappers.ReqresWeatherApiToWeatherMapper
import com.vadimulasevich.yourweather.network.modelsNetworkMainScreen.ReqresWeatherApiResponse
import com.vadimulasevich.yourweather.repository.WeatherNetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreenViewModel(
    private val weatherNetworkRepository: WeatherNetworkRepository,
    private val weatherMapper: ReqresWeatherApiToWeatherMapper
) : ViewModel() {

    private val _localWeatherWeekList = MutableLiveData<ResultState<List<Weather>>>()
    val localWeatherWeekList: LiveData<ResultState<List<Weather>>> = _localWeatherWeekList


    init {
        loadWeatherWeek()
    }

    private fun loadWeatherWeek() {
        _localWeatherWeekList.value = ResultState.Loading()
        weatherNetworkRepository.getWeatherAllNextWeek(object : Callback<ReqresWeatherApiResponse> {
            override fun onResponse(
                call: Call<ReqresWeatherApiResponse>,
                response: Response<ReqresWeatherApiResponse>,
            ) {
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