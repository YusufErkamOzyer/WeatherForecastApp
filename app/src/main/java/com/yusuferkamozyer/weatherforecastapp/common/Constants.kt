package com.yusuferkamozyer.weatherforecastapp.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.yusuferkamozyer.weatherforecastapp.R

object Constants {
    const val api_key="2cac052e5fa8bf383bae4c6822477750"
    const val BASE_URL ="https://api.openweathermap.org/"
    const val BASE_LOCAL_URL="https://api.opencagedata.com/"
    const val api_local_key="65ded17bf9f842128f699befb3464d67"


    val fontFamily= FontFamily(
        Font(R.font.lexend_bold,FontWeight.Bold),
        Font(R.font.lexend_thin,FontWeight.Thin),
        Font(R.font.lexend_black,FontWeight.Black),
        Font(R.font.lexend_medium,FontWeight.Medium),
        Font(R.font.lexend_semibold,FontWeight.SemiBold),
        Font(R.font.lexend_extrabold,FontWeight.ExtraBold),
        Font(R.font.lexend_regular,FontWeight.Normal),
        Font(R.font.lexend_extralight,FontWeight.ExtraLight)
    )

    const val weatherForecastScreen="WeatherForecastScreen"
    const val weeklyWeatherForecastScreen="WeeklyWeatherForecast"
}