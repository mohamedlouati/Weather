package com.louati.repository.weatherRepository

import com.louati.repository.weatherRepository.models.WeatherDTO
import com.louati.repository.weatherRepository.remoteDataSource.WeatherApi
import com.louati.utils.Resource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRepositoryTest {

    @Test
    fun testFetchWeather_Success() = runBlocking {
        // Mock WeatherApi
        val mockedWeather = mock(WeatherDTO::class.java)
        val mockWeatherApi: WeatherApi = mock(WeatherApi::class.java)
        val response = Response.success(mockedWeather)
        val call: Call<WeatherDTO> = mock(response)
        whenever(mockWeatherApi.WEATHER_API.fetchWeather(any(), any(), any())).thenReturn(call)
        doAnswer {
            val callback = it.arguments[0] as Callback<WeatherDTO>
            callback.onResponse(call, response)
        }.`when`(call).enqueue(any())

        // Create WeatherRepository instance with the mock API
        val repository = WeatherRepository(mockWeatherApi)

        // Call the method being tested
        val result = repository.fetchWeather(0.0, 0.0)

        // Check the result
        assertEquals(result, Resource.Success(mockedWeather))
    }

}