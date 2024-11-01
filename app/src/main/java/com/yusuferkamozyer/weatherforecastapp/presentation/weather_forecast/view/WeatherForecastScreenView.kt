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
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO
import com.yusuferkamozyer.weatherforecastapp.domain.model.DailyWeatherModel
import com.yusuferkamozyer.weatherforecastapp.domain.model.HourlyWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.DailyWeatherMenuList
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.HourlyWeatherMenuList
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.toDailyWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list.toHourlyWeatherMenu


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecastScreenView(weatherForecastDTO: WeatherForecastDTO) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .padding(top = 40.dp)
    ) {
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
            Text(weatherForecastDTO.timezone)
            SettingsMenu()
        }
        Spacer(Modifier.height(50.dp))
        DisplayTemperature(
            weatherForecastDTO.current.temp,
            weatherForecastDTO.current.weather[0].main
        )
        val hourlyArrayList= arrayListOf<HourlyWeatherModel>()
        val dailyArrayList= arrayListOf<DailyWeatherModel>()
        weatherForecastDTO.hourly.forEach {
            hourlyArrayList.add(it.toHourlyWeatherMenu())
        }

        weatherForecastDTO.daily.forEach {
            dailyArrayList.add(it.toDailyWeatherModel())
        }
        HourlyWeatherMenuList(hourlyArrayList.toList())
        DailyWeatherMenuList(dailyArrayList.toList())

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
                color = Color.Cyan,
                fontSize = 120.sp,
                fontFamily = Constants.fontFamily,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.width(15.dp))
            Text(
                "°C",
                modifier = Modifier.padding(top = 15.dp),
                color = Color.Cyan,
                fontSize = 50.sp,
                fontFamily = Constants.fontFamily,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = description,
            color = Color.Cyan,
            fontSize = 45.sp,
            fontFamily = Constants.fontFamily
        )

    }
}




