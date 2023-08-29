package com.louati.repository.weatherRepository

import com.louati.repository.weatherRepository.models.WeatherDTO
import com.louati.repository.weatherRepository.remoteDataSource.WeatherApi
import com.louati.utils.API_KEY
import com.louati.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository: WeatherRepositoryInterface {

    override suspend fun fetchWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherDTO> {
        val response = WeatherApi.WEATHER_API.fetchWeather(
            latitude = latitude,
            longitude = longitude,
            appId = API_KEY
        )

        var result: Resource<WeatherDTO>? = null

        response.enqueue(object: Callback<WeatherDTO> {
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                if (response.code() == 200){
                    response.body()?.let {
                        result = Resource.Success(it)
                    } ?: run {
                        result = Resource.Error(isDataEmpty = true)
                    }
                }else {
                    result = Resource.Failure(isNetworkError = false, errorCode = response.code())
                }
            }

            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                result = Resource.Failure(isNetworkError = true)
            }
        })

        return result ?: Resource.Failure(isNetworkError = false)
    }
}