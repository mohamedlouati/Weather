package com.louati.repository.weatherRepository.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temperature(
    @SerialName("afternoon")
    val afternoon: Double,
    @SerialName("evening")
    val evening: Double,
    @SerialName("max")
    val max: Double,
    @SerialName("min")
    val min: Double,
    @SerialName("morning")
    val morning: Double,
    @SerialName("night")
    val night: Double
)