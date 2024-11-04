package com.yusuferkamozyer.weatherforecastapp.presentation

sealed class Screen(val route: String) {
    object WeatherForecastScreen : Screen("weather_forecast_screen")
    object RequestLocationPermissionScreen : Screen("request_location_permission_screen")
    object WeekWeatherForecastScreen : Screen("week_weather_forecast_screen")
}