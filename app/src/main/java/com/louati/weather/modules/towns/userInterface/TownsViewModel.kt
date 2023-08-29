package com.louati.weather.modules.towns.userInterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.louati.repository.roomDataBase.townsRepository.TownsRepository
import com.louati.repository.roomDataBase.townsRepository.TownsRepositoryInterface
import com.louati.utils.Resource
import com.louati.utils.UiStateResult
import com.louati.weather.WeatherApplications
import com.louati.weather.modules.towns.userInterface.models.TownsViewItemInterface
import com.louati.weather.modules.towns.userInterface.models.convertToTownsViewItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TownsViewModel: ViewModel() {

    private var repository: TownsRepositoryInterface = TownsRepository(WeatherApplications.instance)

    private val _townsListState =
        MutableStateFlow<UiStateResult<List<TownsViewItemInterface>>>(UiStateResult.LoadingState())
    val townsListState = _townsListState.asStateFlow()

    private val _insertTownState =
        MutableStateFlow<UiStateResult<Boolean>>(UiStateResult.LoadingState())
    val insertTownState = _insertTownState.asStateFlow()

    fun insertTown(
        name: String,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            val result = repository.insertTown(
                name = name,
                latitude = latitude,
                longitude = longitude
            )
            _insertTownState.value = UiStateResult.DataState(result > 0)
        }
    }

    fun loadTownsListResult() {
        viewModelScope.launch {
            when (val result = repository.fetchTownsList()) {
                is Resource.Success -> {
                    _townsListState.value = UiStateResult.DataState(
                        data = result.value.map { it.convertToTownsViewItem() }
                    )
                }

                is Resource.Error -> {
                    if (result.isDataEmpty) {
                        _townsListState.value = UiStateResult.EmptyState
                    } else {
                        _townsListState.value = UiStateResult.UnknownState
                    }
                }

                is Resource.Failure -> {
                    _townsListState.value = UiStateResult.UnknownState
                }
            }
        }
    }
}