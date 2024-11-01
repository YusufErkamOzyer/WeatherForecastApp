package com.yusuferkamozyer.weatherforecastapp.domain.repository

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
    ): WeatherForecastDTO


}