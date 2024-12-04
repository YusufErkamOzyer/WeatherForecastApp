package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.common.Utils
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.openweatherdto.Temp

import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel
import com.yusuferkamozyer.weatherforecastapp.presentation.Screen
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.LocationInformationState
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.DailyWeatherMenuList
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.HourlyWeatherMenuList
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.toDailyWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.toHourlyWeatherMenu


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecastScreenView(
    weatherForecastModel: WeatherForecastModel,
    lazyListState: LazyListState,
    navController: NavController,
    locationInformationState: LocationInformationState,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.inversePrimaryLightMediumContrast)
    ) {

        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppColors.inversePrimaryLightMediumContrast)
        ) {
            item {
                Spacer(Modifier.height(50.dp))
                DisplayTemperature(
                    weatherForecastModel.current.temp,
                    weatherForecastModel.current.weather[0].main,
                    weatherForecastModel.daily[0].temp
                )
            }
            item {
                val hourlyList = weatherForecastModel.hourly.map { it.toHourlyWeatherMenu() }
                HourlyWeatherMenuList(hourlyList)
            }
            item {
                val dailyList = weatherForecastModel.daily.map { it.toDailyWeatherModel() }
                DailyWeatherMenuList(
                    dailyList,
                    navController,
                    weatherForecastModel.lat,
                    weatherForecastModel.lon
                )
            }
            item {
                val today = weatherForecastModel.daily[0]
                TodayWeatherForecastView(
                    description = today.summary,
                    humidity = today.humidity,
                    feelsLikeDay = today.feels_like.day,
                    uvi = today.uvi,
                    pressure = today.pressure,
                    clouds = today.clouds,
                    sunset = Utils.formatUtcToTimeString(today.sunset),
                    sunrise = Utils.formatUtcToTimeString(today.sunrise)
                )
            }
        }
    }

}


@Composable
fun DisplayTemperature(temp: Double, description: String, tempHighLow: Temp) {
    val celsius = Utils.kelvinToCelsius(temp)
    val tempHigh = Utils.kelvinToCelsius(tempHighLow.max)
    val tempLow = Utils.kelvinToCelsius(tempHighLow.min)
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Text(
                celsius,
                color = Color.White,
                fontSize = 120.sp,
                fontFamily = Constants.fontFamily,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.width(15.dp))
            Text(
                "°C",
                modifier = Modifier.padding(top = 15.dp),
                color = Color.White,
                fontSize = 50.sp,
                fontFamily = Constants.fontFamily,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = description,
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = Constants.fontFamily
        )
        Text(
            text = "$tempLow°C / $tempHigh°C",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = Constants.fontFamily
        )
    }
}