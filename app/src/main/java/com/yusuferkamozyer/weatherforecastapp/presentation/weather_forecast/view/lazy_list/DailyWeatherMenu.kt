package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.lazy_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yusuferkamozyer.weatherforecastapp.R
import com.yusuferkamozyer.weatherforecastapp.common.Utils
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.Daily
import com.yusuferkamozyer.weatherforecastapp.domain.model.DailyWeatherModel

@Composable
fun DailyWeatherMenuList(dailyWeatherModelList: List<DailyWeatherModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dailyWeatherModelList) { item ->
            DailyWeatherMenuItem(item)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun Daily.toDailyWeatherModel(): DailyWeatherModel {
    return DailyWeatherModel(
        url = Utils.urlCreator(this.weather[0].icon),
        day = Utils.getDayFromUtcMillis(
            this.dt
        ),
        description = this.weather[0].description,
        kelvinMax = this.temp.max,
        kelvinMin = this.temp.min
    )
}


@Composable
fun DailyWeatherMenuItem(dailyWeatherModel: DailyWeatherModel) {
    Row {
        AsyncImage(
            model = dailyWeatherModel.url,
            contentDescription = dailyWeatherModel.description,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.fail)
        )
        Spacer(Modifier.width(15.dp))
        Text(text = dailyWeatherModel.day)
        Spacer(Modifier.width(5.dp))
        Text(text = dailyWeatherModel.description)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            val kelvinMin = Utils.kelvinToCelsius(dailyWeatherModel.kelvinMin) + "°C"
            val kelvinMax = Utils.kelvinToCelsius(dailyWeatherModel.kelvinMax) + "°C"
            Text(
                text = "$kelvinMin / $kelvinMax"
            )

        }


    }
}