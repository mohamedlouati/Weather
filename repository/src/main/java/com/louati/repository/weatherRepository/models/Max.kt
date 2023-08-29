package com.louati.repository.weatherRepository.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Max(
    @SerialName("direction")
    val direction: Int,
    @SerialName("speed")
    val speed: Double
)