package com.louati.repository.weatherRepository.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pressure(
    @SerialName("afternoon")
    val afternoon: Int
)