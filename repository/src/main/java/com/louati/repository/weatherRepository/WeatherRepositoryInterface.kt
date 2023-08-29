package com.louati.repository.weatherRepository

import com.louati.repository.weatherRepository.models.WeatherDTO
import com.louati.utils.Resource

interface WeatherRepositoryInterface {
    suspend fun fetchWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherDTO>
}