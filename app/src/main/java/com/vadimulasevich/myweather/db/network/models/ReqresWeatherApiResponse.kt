package com.vadimulasevich.myweather.db.network.models

data class ReqresWeatherApiResponse(
    val dt: Int,
    val main: MainNetwork,
    val name: String,
    val sys: SysNetwork,
    val weather: List<ReqresWeather>,
    val wind: WindNetwork
)