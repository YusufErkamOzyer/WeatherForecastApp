package com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto

import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel

data class WeatherForecastDTO(
    val alerts: List<Alert>,
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int,
)

fun WeatherForecastDTO.toWeatherForecastModel(): WeatherForecastModel {
    return WeatherForecastModel(
        current = current,
        daily = daily,
        hourly = hourly,
        lat = lat,
        lon = lon,
        minutely = minutely,
        timezone = timezone,
        timezone_offset = timezone_offset
    )
}