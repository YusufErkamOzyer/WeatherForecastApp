package com.yusuferkamozyer.weatherforecastapp.data.repository

import com.yusuferkamozyer.weatherforecastapp.data.remote.OpenCageGeocodingApi
import com.yusuferkamozyer.weatherforecastapp.data.remote.OpenWeatherApi
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.geocodingdto.LocalInformationDTO
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.WeatherForecastDTO
import com.yusuferkamozyer.weatherforecastapp.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val api_geo: OpenCageGeocodingApi,
) :
    WeatherForecastRepository {
    override suspend fun getWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
    ): WeatherForecastDTO {
        return api.getWeatherForecast(latitude, longitude, exclude, apiKey)
    }

    override suspend fun getLocalInformation(latlon: String): LocalInformationDTO {
        return api_geo.getLocalName(latlon)
    }
}