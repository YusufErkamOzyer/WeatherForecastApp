package com.yusuferkamozyer.weatherforecastapp.presentation.week_weather_forecast.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeekWeatherModel
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyWeatherForecastScreenView(
    modifier: Modifier = Modifier,
    weatherForecastModel: WeatherForecastModel,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MediumTopAppBar(
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = weatherForecastModel.timezone,
                            color = Color.White,
                            fontFamily = Constants.fontFamily
                        )
                        Text(
                            text = "Weather Forecasts for 7 Days",
                            color = Color.White,
                            fontFamily = Constants.fontFamily
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Return to Previous Page"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Go Settings"
                        )
                    }
                },
                colors = TopAppBarColors(
                    containerColor = AppColors.inversePrimaryLightMediumContrast,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    scrolledContainerColor = Color.White
                )
            )
        },
        containerColor = AppColors.inversePrimaryLightMediumContrast
    ) { value ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(value),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val weekList = weatherForecastModel.daily.map { it.toWeekWeatherModel() }
            WeekWeatherForecastLazyList(weekList)
        }

    }


}