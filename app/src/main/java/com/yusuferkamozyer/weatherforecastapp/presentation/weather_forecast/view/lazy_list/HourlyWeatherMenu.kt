package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yusuferkamozyer.weatherforecastapp.R
import com.yusuferkamozyer.weatherforecastapp.common.Utils
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Hourly
import com.yusuferkamozyer.weatherforecastapp.domain.model.HourlyWeatherModel


@Composable
fun HourlyWeatherMenuList(hourlyWeatherModelList: List<HourlyWeatherModel>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(hourlyWeatherModelList) { item ->
            HourlyWeatherMenuItem(item)
        }
    }
}

fun Hourly.toHourlyWeatherMenu(): HourlyWeatherModel {
    return HourlyWeatherModel(
        kelvin = this.temp,
        url = Utils.urlCreator(this.weather[0].icon),
        utcTime = this.dt,
        windSpeed = this.wind_speed,
        description = this.weather[0].description
    )
}


@Composable
fun HourlyWeatherMenuItem(hourlyWeatherModel: HourlyWeatherModel) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
        // İlk Text
        Text(
            text = Utils.kelvinToCelsius(hourlyWeatherModel.kelvin),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Image
        AsyncImage(
            model = hourlyWeatherModel.url,
            contentDescription = hourlyWeatherModel.description,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.fail)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // İkinci Text
        Text(
            text = hourlyWeatherModel.windSpeed.toString() + " m/s",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Üçüncü Text
        Text(
            text = Utils.formatUtcToTimeString(hourlyWeatherModel.utcTime),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}