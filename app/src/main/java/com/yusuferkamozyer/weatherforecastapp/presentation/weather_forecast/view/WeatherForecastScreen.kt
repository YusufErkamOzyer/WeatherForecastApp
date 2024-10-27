package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model.WeatherForecastViewModel

@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),
) {
    val latitude = 37.7749
    val longitude = -122.4194
    val exclude = "hourly"
    val api_key = Constants.api_key
    LaunchedEffect(Unit) {
        viewModel.getWeatherForecast(latitude, longitude, exclude, api_key) }

    val state = viewModel.state.value
    state.weatherForecastDTO?.let {
        println(it.lat)
    }
    if (state.error.isNotBlank()) {
        println(state.error)
    }
    if (state.isLoading) {
        println("Yükleniyor")
    }


}