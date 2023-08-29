package com.louati.weather.modules.weather.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.louati.repository.weatherRepository.WeatherRepository
import com.louati.utils.Resource
import com.louati.utils.UiStateResult
import com.louati.weather.modules.weather.userInterface.models.WeatherViewItemInterface
import com.louati.weather.modules.weather.userInterface.models.convertToWeatherViewItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private val _weatherResponse =
        MutableStateFlow<UiStateResult<WeatherViewItemInterface>>(UiStateResult.LoadingState())
    val weatherResponse get() = _weatherResponse.asStateFlow()

    fun loadWeatherResult(
        latitude: Double,
        longitude: Double,
        notifyLoadWeatherLoading: () -> Unit,
        notifyLoadWeatherResult: (UiStateResult<WeatherViewItemInterface>) -> Unit,
    ) {
        notifyLoadWeatherLoading()
        viewModelScope.launch {
            when (val response = repository.fetchWeather(latitude, longitude)) {
                is Resource.Success -> {
                    val weatherItem = response.value.convertToWeatherViewItem()
                    notifyLoadWeatherResult(UiStateResult.DataState(weatherItem))
                    _weatherResponse.value = UiStateResult.DataState(weatherItem)
                }

                is Resource.Error -> {
                    if (response.isDataEmpty) {
                        notifyLoadWeatherResult(UiStateResult.EmptyState)
                        _weatherResponse.value = UiStateResult.EmptyState
                    } else {
                        notifyLoadWeatherResult(UiStateResult.ServerErrorState)
                        _weatherResponse.value = UiStateResult.ServerErrorState
                    }
                }

                is Resource.Failure -> {
                    notifyLoadWeatherResult(UiStateResult.ServerErrorState)
                    _weatherResponse.value = UiStateResult.ServerErrorState
                }
            }
        }
    }
}