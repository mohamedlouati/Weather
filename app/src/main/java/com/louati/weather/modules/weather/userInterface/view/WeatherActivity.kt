package com.louati.weather.modules.weather.userInterface.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.louati.utils.UiStateResult
import com.louati.weather.databinding.ActivityWeatherBinding
import com.louati.weather.modules.weather.userInterface.WeatherViewModel
import kotlinx.coroutines.launch
import java.util.Date

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        viewModel.loadWeatherResult(
            latitude = intent.getDoubleExtra("latitude", 0.0),
            longitude = intent.getDoubleExtra("longitude", 0.0),
            notifyLoadWeatherLoading = {},
            notifyLoadWeatherResult = {
                observeWeather()
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun observeWeather() {
        this.lifecycleScope.launch {
            viewModel.weatherResponse.collect { uisState ->
                when (uisState) {
                    is UiStateResult.LoadingState -> {
                        binding.tvData.text = "loading"
                    }
                    is UiStateResult.DataState -> {
                        uisState.data?.let {
                            binding.tvData.text = it.toString()
                        }
                    }
                    UiStateResult.EmptyState -> {
                        binding.tvData.text = "no data"
                    }
                    else -> {
                        binding.tvData.text = "error"
                    }
                }
            }
        }
    }
}