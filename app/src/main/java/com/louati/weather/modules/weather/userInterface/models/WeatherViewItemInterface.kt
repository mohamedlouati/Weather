package com.louati.weather.modules.weather.userInterface.models

import com.louati.repository.weatherRepository.models.WeatherDTO

interface WeatherViewItemInterface {
    val date: String
    val lat: Int
    val lon: Int
}

data class WeatherViewItem(
    override val date: String,
    override val lat: Int,
    override val lon: Int
): WeatherViewItemInterface

fun WeatherDTO.convertToWeatherViewItem(): WeatherViewItemInterface =
    WeatherViewItem(
        date = this.date,
        lat = this.lat,
        lon = this.lon
    )