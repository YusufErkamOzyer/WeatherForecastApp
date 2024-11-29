package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast

import com.yusuferkamozyer.weatherforecastapp.domain.model.LocalInformationModel
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel

data class LocationInformationState (
    val isLoading:Boolean=false,
    val localInformationModel: LocalInformationModel?=null,
    val error: String = ""
)