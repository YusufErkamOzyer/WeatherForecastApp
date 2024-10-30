package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.yusuferkamozyer.weatherforecastapp.common.Resource
import com.yusuferkamozyer.weatherforecastapp.data.local.LocationTracker
import com.yusuferkamozyer.weatherforecastapp.domain.model.LocationState
import com.yusuferkamozyer.weatherforecastapp.domain.use_case.GetWeatherForecastUseCase
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.WeatherForecastState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val fusedLocationClient: FusedLocationProviderClient,
    application: Application
) : AndroidViewModel(application) {
    private val _state = mutableStateOf(WeatherForecastState())
    val state: State<WeatherForecastState> = _state

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    fun getWeatherForecast(latitude: Double, longitude: Double, exclude: String, apiKey: String) {
        getWeatherForecastUseCase(latitude, longitude, exclude, apiKey).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = WeatherForecastState(isLoading = true)

                }

                is Resource.Success -> {
                    _state.value = WeatherForecastState(weatherForecastDTO = result.data)
                }

                is Resource.Error -> {
                    _state.value = WeatherForecastState(
                        error =result.message ?: "An unexpected error occured"
                    )
                    println("hata alındı")
                }
            }

        }.launchIn(viewModelScope)
    }

    fun fetchLocation() {
        val context = getApplication<Application>().applicationContext
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
            _location.value = loc
        }
    }




}