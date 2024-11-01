package com.yusuferkamozyer.weatherforecastapp.domain.model



data class HourlyWeatherModel (
    val kelvin:Double,
    val url:String,
    val utcTime:Long,
    val windSpeed:Double,
    val description: String
)