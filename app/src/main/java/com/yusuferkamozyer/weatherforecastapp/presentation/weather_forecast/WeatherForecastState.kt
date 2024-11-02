package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel

data class WeatherForecastState (
    val isLoading:Boolean=false,
    val weatherForecastModel: WeatherForecastModel?=null,
    val error: String = ""
)
