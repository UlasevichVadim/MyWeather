package com.vadimulasevich.yourweather.mappers

import com.vadimulasevich.yourweather.db.models.Weather
import com.vadimulasevich.yourweather.network.modelsNetworkMainScreen.ReqresWeather
import org.junit.Assert.*

import org.junit.Test

class ReqresWeatherApiToWeatherMapperTest {


    val sut = ReqresWeatherApiToWeatherMapper()

    @Test
    fun toWeather() {

        val regresWeather = ReqresWeather(
            "description",
            "icon",
            1,
            "main"
        )
        val expected = Weather(
            1,
            "icon",
            "description",
            "main"
        )
        val actual = sut.toWeather(regresWeather)

        assertEquals(expected, actual)

    }


    @Test
    fun toWeatherList() {
    }
}