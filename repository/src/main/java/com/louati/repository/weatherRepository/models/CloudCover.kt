package com.louati.repository.weatherRepository.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CloudCover(
    @SerialName("afternoon")
    val afternoon: Int
)