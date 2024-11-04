package com.yusuferkamozyer.weatherforecastapp.presentation.week_weather_forecast.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuferkamozyer.weatherforecastapp.common.Resource
import com.yusuferkamozyer.weatherforecastapp.domain.use_case.GetWeatherForecastUseCase
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.WeatherForecastState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class WeekWeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
): ViewModel(){
    private val _state = mutableStateOf(WeatherForecastState())
    val state: State<WeatherForecastState> = _state


    fun getWeatherForecast(latitude: Double, longitude: Double, exclude: String, apiKey: String) {
        getWeatherForecastUseCase(latitude, longitude, exclude, apiKey).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = WeatherForecastState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = WeatherForecastState(weatherForecastModel = result.data)
                }
                is Resource.Error -> {
                    _state.value = WeatherForecastState(
                        error = result.message ?: "An unexpected error occured"
                    )
                    println("hata alındı")
                }
            }
        }.launchIn(viewModelScope)
    }

}