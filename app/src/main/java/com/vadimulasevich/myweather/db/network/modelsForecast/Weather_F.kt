package com.vadimulasevich.myweather.db.network.modelsForecast

data class Weather_F(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)