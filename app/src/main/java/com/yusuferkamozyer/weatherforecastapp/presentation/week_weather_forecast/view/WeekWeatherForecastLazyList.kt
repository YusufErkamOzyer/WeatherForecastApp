package com.yusuferkamozyer.weatherforecastapp.presentation.week_weather_forecast.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.yusuferkamozyer.weatherforecastapp.R
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.common.Utils
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Daily
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeekWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors

@Composable
fun WeekWeatherForecastLazyList(
    weekWeatherModelList: List<WeekWeatherModel>,
) {
    Card(
        modifier = Modifier.padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.primaryLight
        )
    ) {
        LazyRow {
            items(weekWeatherModelList) { item ->
                WeekWeatherForecastLazyListItem(item, modifier = Modifier.padding(5.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Daily.toWeekWeatherModel(): WeekWeatherModel {
    return WeekWeatherModel(
        day = Utils.getDayFromUtcMillis(this.dt),
        url = Utils.urlCreator(this.weather[0].icon),
        tempDay = this.temp.day,
        tempNight = this.temp.night,
        moonPhase = this.moon_phase.toString(),
        description = this.weather[0].description
    )
}

@Composable
fun WeekWeatherForecastLazyListItem(
    weekWeatherModel: WeekWeatherModel,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = weekWeatherModel.day,
            color = Color.White,
            fontFamily = Constants.fontFamily,
            modifier = modifier
        )
        Text(
            text = Utils.kelvinToCelsius(weekWeatherModel.tempDay)+"°C",
            color = Color.White,
            fontFamily = Constants.fontFamily,
            modifier = modifier
        )
        AsyncImage(
            model = weekWeatherModel.url,
            contentDescription = weekWeatherModel.description,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.fail),
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = Utils.kelvinToCelsius(weekWeatherModel.tempNight)+"°C",
            color = Color.White,
            fontFamily = Constants.fontFamily,
            modifier = modifier
        )
        Text(
            text = weekWeatherModel.moonPhase,
            color = Color.White,
            fontFamily = Constants.fontFamily,
            modifier = modifier
        )
    }
}