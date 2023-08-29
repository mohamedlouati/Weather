package com.louati.repository.weatherRepository.remoteDataSource

import com.louati.repository.RetrofitInstance
import com.louati.repository.weatherRepository.models.WeatherDTO
import com.louati.utils.API_KEY
import retrofit2.Call
import retrofit2.http.*

interface WeatherApiInterface {

    @POST("onecall?")
    fun fetchWeather(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double,
        @Query(APP_ID) appId: String
    ): Call<WeatherDTO>

    companion object{
        const val LATITUDE = "lat"
        const val LONGITUDE = "lon"
        const val APP_ID = "APPID"
    }
}

object WeatherApi {
    val WEATHER_API : WeatherApiInterface by lazy {
        RetrofitInstance().retrofit.create(WeatherApiInterface::class.java)
    }
}