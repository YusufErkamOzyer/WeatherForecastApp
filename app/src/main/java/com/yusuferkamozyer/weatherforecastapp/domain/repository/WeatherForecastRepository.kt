package com.yusuferkamozyer.weatherforecastapp.domain.repository

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.geocodingdto.LocalInformationDTO
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.WeatherForecastDTO

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
    ): WeatherForecastDTO

    suspend fun getLocalInformation(latlon: String): LocalInformationDTO

}