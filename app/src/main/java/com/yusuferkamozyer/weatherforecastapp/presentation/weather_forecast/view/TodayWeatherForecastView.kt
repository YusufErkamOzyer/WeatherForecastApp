package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.common.Utils
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors

@Composable
fun TodayWeatherForecastView(
    description: String, humidity: Int,
    feelsLikeDay: Double,
    uvi: Double,
    pressure: Int,
    clouds: Int,
    sunrise: String,
    sunset: String,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight().fillMaxSize(0.5f)
            ,
        ) {
            WeatherDescriptionText(description)
            Spacer(modifier = Modifier.height(13.dp))
            SunLocationInfoCard(sunrise, sunset)

        }
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            DailyWeatherForecastDetails(humidity, feelsLikeDay, uvi, pressure, clouds)
        }
    }
}

@Composable
fun WeatherDescriptionText(description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.primaryLight
        )
    ) {
        Text(
            text = description,
            fontSize = 15.sp,
            fontFamily = Constants.fontFamily,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
        )
    }
}

@Composable
fun SunLocationInfoCard(sunrise: String, sunset: String) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.primaryLight
        )
    ) {
        Column {
            DailyWeatherForecastDetailsRow("Sunrise: ", sunrise)
            DailyWeatherForecastDetailsRow("Sunset : ", sunset)
        }
    }
}

@Composable
fun DailyWeatherForecastDetails(
    humidity: Int,
    feelsLikeDay: Double,
    uvi: Double,
    pressure: Int,
    clouds: Int,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.primaryLight
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            DailyWeatherForecastDetailsRow("Humidity", "${humidity}%")
            DailyWeatherForecastDetailsRow("Feels", Utils.kelvinToCelsius(feelsLikeDay) + "°C")
            DailyWeatherForecastDetailsRow("UV", uvi.toString())
            DailyWeatherForecastDetailsRow("Pressure", "${pressure} hPa")
            DailyWeatherForecastDetailsRow("Clouds", "${clouds} %")
        }
    }
}

@Composable
fun DailyWeatherForecastDetailsRow(text1: String, text2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 5.dp, end = 5.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(0.5f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text1,
                fontFamily = Constants.fontFamily,
                color = Color.White

            )
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = text2,
                fontFamily = Constants.fontFamily,
                color = Color.White
            )
        }
    }
}
