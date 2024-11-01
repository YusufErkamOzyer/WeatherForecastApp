package com.yusuferkamozyer.weatherforecastapp.domain.model



data class DailyWeatherModel (
    val url:String,
    val day:String,
    val description: String,
    val kelvinMax:Double,
    val kelvinMin:Double
)