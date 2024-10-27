package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO

data class WeatherForecastState (
    val isLoading:Boolean=false,
    val weatherForecastDTO: WeatherForecastDTO?=null,
    val error: String = ""
)
