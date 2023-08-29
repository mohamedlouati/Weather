package com.louati.repository.weatherRepository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    @SerialName("cloud_cover")
    val cloudCover: CloudCover,
    @SerialName("date")
    val date: String,
    @SerialName("humidity")
    val humidity: Humidity,
    @SerialName("lat")
    val lat: Int,
    @SerialName("lon")
    val lon: Int,
    @SerialName("precipitation")
    val precipitation: Precipitation,
    @SerialName("pressure")
    val pressure: Pressure,
    @SerialName("temperature")
    val temperature: Temperature,
    @SerialName("tz")
    val tz: String,
    @SerialName("units")
    val units: String,
    @SerialName("wind")
    val wind: Wind
)