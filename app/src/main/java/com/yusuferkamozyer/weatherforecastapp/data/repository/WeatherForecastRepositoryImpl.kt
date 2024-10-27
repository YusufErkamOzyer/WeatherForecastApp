package com.yusuferkamozyer.weatherforecastapp.data.repository

import com.yusuferkamozyer.weatherforecastapp.data.remote.OpenWeatherApi
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO
import com.yusuferkamozyer.weatherforecastapp.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(private val api: OpenWeatherApi) :
    WeatherForecastRepository {
    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
    ): WeatherForecastDTO {
        return api.getWeatherForecast(latitude, longitude, exclude, apiKey)
    }
}