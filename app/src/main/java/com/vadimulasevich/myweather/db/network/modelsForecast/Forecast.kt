package com.vadimulasevich.myweather.db.network.modelsForecast

data class Forecast(
    val clouds: Clouds_F,
    val dt: Int,
    val dt_txt: String,
    val main: Main_F,
    val pop: Double,
    val rain: Rain_F,
    val sys: Sys_F,
    val visibility: Int,
    val weather: List<Weather_F>,
    val wind: Wind_F
)