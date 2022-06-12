package com.vadimulasevich.yourweather.network.modelsNetwork

data class ReqresWeatherApiResponse(
    val dt: Int,
    val main: MainNetwork,
    val name: String,
    val sys: SysNetwork,
    val weather: List<ReqresWeather>,
    val wind: WindNetwork
)