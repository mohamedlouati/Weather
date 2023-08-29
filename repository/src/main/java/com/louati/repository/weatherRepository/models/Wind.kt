package com.louati.repository.weatherRepository.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    @SerialName("max")
    val max: Max
)