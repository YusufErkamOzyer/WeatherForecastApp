package com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto

data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Long,
    val moonset: Long,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val summary: String,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)