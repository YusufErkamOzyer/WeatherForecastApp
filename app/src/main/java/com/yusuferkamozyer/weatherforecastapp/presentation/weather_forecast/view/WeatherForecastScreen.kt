package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.yusuferkamozyer.weatherforecastapp.R
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.common.Utils
import com.yusuferkamozyer.weatherforecastapp.domain.model.HourlyWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model.WeatherForecastViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.inversePrimaryLightMediumContrast
    ) {
        val location by viewModel.location.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.fetchLocation()

        }
        val api_key = Constants.api_key
        LaunchedEffect(location) {
            location?.let {
                viewModel.getWeatherForecast(it.latitude, it.longitude, "", api_key)
            } ?: println("Konum bilgisi henüz alınmadı")
        }
        val state = viewModel.state.value
        state.weatherForecastModel?.let { dailyState ->
            WeatherForecastScreenView(dailyState)

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








