package com.vadimulasevich.yourweather.network.modelsNetwork

data class WeatherApiResponse(
    val dt: Int,
    val main: MainNetwork,
    val name: String,
    val sys: SysNetwork,
    val weather: List<WeatherNetwork>,
    val wind: WindNetwork
)