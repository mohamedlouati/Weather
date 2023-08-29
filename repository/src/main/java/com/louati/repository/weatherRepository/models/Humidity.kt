package com.louati.repository.weatherRepository.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Humidity(
    @SerialName("afternoon")
    val afternoon: Int
)