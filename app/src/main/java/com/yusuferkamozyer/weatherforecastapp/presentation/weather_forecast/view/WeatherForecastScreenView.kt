package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.common.Utils

import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.DailyWeatherMenuList
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.HourlyWeatherMenuList
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.toDailyWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.toHourlyWeatherMenu


@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun WeatherForecastScreenView(weatherForecastModel: WeatherForecastModel) {
    LazyColumn(
        modifier = Modifier
            .padding(20.dp)
            .padding(top = 40.dp)
    ) {
        //ToolBar
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add Something",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            println("Clicked")
                        })
                Text(weatherForecastModel.timezone)
                SettingsMenu()
            }
            Spacer(Modifier.height(50.dp))

            DisplayTemperature(
                weatherForecastModel.current.temp,
                weatherForecastModel.current.weather[0].main
            )
        }
        item {
            val hourlyList = weatherForecastModel.hourly.map { it.toHourlyWeatherMenu() }
            HourlyWeatherMenuList(hourlyList)
        }
        item {
            val dailyList = weatherForecastModel.daily.map { it.toDailyWeatherModel() }
            DailyWeatherMenuList(dailyList)
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

@Composable
fun DisplayTemperature(temp: Double, description: String) {
    val celsius = Utils.kelvinToCelsius(temp)
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
            fontSize = 45.sp,
            fontFamily = Constants.fontFamily
        )

    }
}




