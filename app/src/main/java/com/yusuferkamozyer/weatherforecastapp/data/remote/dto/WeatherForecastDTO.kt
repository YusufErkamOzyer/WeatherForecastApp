package com.yusuferkamozyer.weatherforecastapp.data.remote.dto

data class WeatherForecastDTO(
    val alerts: List<Any>,
    val current: Current,
    val daily: List<Any>,
    val hourly: List<Any>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Any>,
    val timezone: String,
    val timezone_offset: Int
)