package com.vadimulasevich.myweather.network.modelsForecast

data class Forecast(
    val clouds: CloudsForecast,
    val dt: Int,
    val dt_txt: String,
    val main: MainForecast,
    val pop: Double,
    val rain: RainForecast,
    val sys: SysForecast,
    val visibility: Int,
    val weather: List<WeatherForecast>,
    val wind: WindForecast
)