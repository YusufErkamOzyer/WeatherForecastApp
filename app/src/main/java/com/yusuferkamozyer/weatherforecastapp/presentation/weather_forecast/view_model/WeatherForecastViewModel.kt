package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.yusuferkamozyer.weatherforecastapp.common.Resource
import com.yusuferkamozyer.weatherforecastapp.domain.use_case.GetLocalInformationUseCase
import com.yusuferkamozyer.weatherforecastapp.domain.use_case.GetWeatherForecastUseCase
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.LocationInformationState
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.WeatherForecastState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val getLocalInformationUseCase: GetLocalInformationUseCase,
    application: Application,
) : AndroidViewModel(application) {
    private val _state = mutableStateOf(WeatherForecastState())
    val state: State<WeatherForecastState> = _state

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    private val _locationInfo = mutableStateOf(LocationInformationState())
    val locationInfo: State<LocationInformationState> = _locationInfo



    fun getLocationInformation(latlon: String) {
        getLocalInformationUseCase(latlon).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _locationInfo.value = LocationInformationState(isLoading = true)
                }
                is Resource.Success -> {
                    _locationInfo.value =
                        LocationInformationState(localInformationModel = result.data)
                }
                is Resource.Error -> {
                    _locationInfo.value = LocationInformationState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

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
