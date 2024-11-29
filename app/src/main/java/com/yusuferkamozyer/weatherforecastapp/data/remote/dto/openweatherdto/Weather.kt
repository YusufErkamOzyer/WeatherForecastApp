package com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)