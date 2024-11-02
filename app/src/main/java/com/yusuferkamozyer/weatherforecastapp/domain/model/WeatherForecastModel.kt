package com.yusuferkamozyer.weatherforecastapp.domain.model

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Current
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Daily
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Hourly
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Minutely

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
