package com.vadimulasevich.yourweather.network.modelsNetworkMainScreen

data class ReqresWeatherApiResponse(
    val dt: Int,
    val main: MainNetwork,
    val name: String,
    val sys: SysNetwork,
    val weather: List<ReqresWeather>,
    val wind: WindNetwork
)