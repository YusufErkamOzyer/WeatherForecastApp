package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast

import com.yusuferkamozyer.weatherforecastapp.domain.model.ApiModel


data class ApiState(
    val isLoading:Boolean=false,
    val apiModel: ApiModel?=null,
    val error: String = ""
)