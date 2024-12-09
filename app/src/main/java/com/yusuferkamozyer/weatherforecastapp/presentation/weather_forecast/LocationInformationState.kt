package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast

import com.yusuferkamozyer.weatherforecastapp.domain.model.LocalInformationModel

data class LocationInformationState (
    val isLoading:Boolean=false,
    val localInformationModel: LocalInformationModel?=null,
    val error: String = ""
)