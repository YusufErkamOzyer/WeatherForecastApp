package com.yusuferkamozyer.weatherforecastapp.data.remote

import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

    @GET("data/3.0/onecall")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String
    ): WeatherForecastDTO
}