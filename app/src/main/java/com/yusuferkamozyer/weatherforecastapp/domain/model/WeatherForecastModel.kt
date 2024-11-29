package com.yusuferkamozyer.weatherforecastapp.domain.model

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.Current
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.Daily
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.Hourly
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.Minutely

data class WeatherForecastModel(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int
)
