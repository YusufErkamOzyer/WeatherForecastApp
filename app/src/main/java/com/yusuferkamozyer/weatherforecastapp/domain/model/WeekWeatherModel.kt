package com.yusuferkamozyer.weatherforecastapp.domain.model

data class WeekWeatherModel(
    val day:String,
    val url:String,
    val tempDay:Double,
    val tempNight:Double,
    val moonPhase:String,
    val description: String
)