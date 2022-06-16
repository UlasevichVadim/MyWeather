package com.vadimulasevich.myweather.ui.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vadimulasevich.myweather.db.local.models.Weather
import com.vadimulasevich.myweather.db.network.modelsForecast.ReceivedWeatherApiForecastResponse
import com.vadimulasevich.myweather.utils.ResultState
import com.vadimulasevich.myweather.mappers.ReceivedWeatherApiToWeatherMapper
import com.vadimulasevich.myweather.db.repositories.network.WeatherRepositoryNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchScreenViewModel(
    private val weatherNetworkRepository: WeatherRepositoryNetwork,
    private val weatherMapper: ReceivedWeatherApiToWeatherMapper,
) : ViewModel() {

    private val _localWeatherWeekList = MutableLiveData<ResultState<List<Weather>>>()
    val localWeatherWeekList: LiveData<ResultState<List<Weather>>> = _localWeatherWeekList

    fun searchWeather(cityName: String) {
        _localWeatherWeekList.value = ResultState.Loading()
        weatherNetworkRepository.getWeatherForecast(
            cityName,
            object : Callback<ReceivedWeatherApiForecastResponse> {
                override fun onResponse(
                    call: Call<ReceivedWeatherApiForecastResponse>,
                    response: Response<ReceivedWeatherApiForecastResponse>,
                ) {
                    val responseBody = response.body()

                    if (responseBody == null) {
                        _localWeatherWeekList.value =
                            ResultState.Error(RuntimeException("Response body is null"))
                        return
                    }

                    val weatherList = weatherMapper.toForecastList(responseBody)
                    _localWeatherWeekList.value = ResultState.Success(weatherList)
                }

                override fun onFailure(
                    call: Call<ReceivedWeatherApiForecastResponse>,
                    t: Throwable,
                ) {
                    _localWeatherWeekList.value = ResultState.Error(t)
                }

            })
    }
}