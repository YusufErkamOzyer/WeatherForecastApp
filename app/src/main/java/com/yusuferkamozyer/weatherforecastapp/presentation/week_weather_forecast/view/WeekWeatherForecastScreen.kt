package com.yusuferkamozyer.weatherforecastapp.presentation.week_weather_forecast.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.pulltorefreshcompose.PullToRefreshWeatherColumn
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model.WeatherForecastViewModel
import com.yusuferkamozyer.weatherforecastapp.presentation.week_weather_forecast.view_model.WeekWeatherForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekWeatherForecastScreen(
    navController: NavController,
    lat: Double,
    lon: Double,
    viewModel: WeekWeatherForecastViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.inversePrimaryLightMediumContrast
    ) {
        val api_key = Constants.api_key
        LaunchedEffect(Unit) {
            viewModel.getWeatherForecast(lat, lon, "", api_key)
        }

        val state = viewModel.state.value
        state.weatherForecastModel?.let { weatherForecastModel ->
            DailyWeatherForecastScreenView(weatherForecastModel = weatherForecastModel)
        }
        if (state.error.isNotBlank()) {
            println(state.error)
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = Color.White
                )
            }
        }
    }

}

