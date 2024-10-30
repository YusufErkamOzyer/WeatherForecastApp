package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model.WeatherForecastViewModel


@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel()
) {

    val location by viewModel.location.collectAsState()
    LaunchedEffect  (Unit){
        viewModel.fetchLocation()
    }
    val exclude = "daily"
    val api_key = Constants.api_key
    LaunchedEffect(location) {
        location?.let {
            viewModel.getWeatherForecast(it.latitude, it.longitude, exclude, api_key)
        } ?: println("Konum bilgisi henüz alınmadı")

    }

    val state = viewModel.state.value
    state.weatherForecastDTO?.let {
        println(it.current.weather[0].description)
    }
    if (state.error.isNotBlank()) {
        println(state.error)
    }
    if (state.isLoading) {
        println("Yükleniyor")
    }



}


