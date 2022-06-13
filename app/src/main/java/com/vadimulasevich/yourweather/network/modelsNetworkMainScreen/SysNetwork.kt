package com.vadimulasevich.yourweather.network.modelsNetworkMainScreen

data class SysNetwork(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)