package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.pulltorefreshcompose.PullToRefreshWeatherColumn
import com.yusuferkamozyer.weatherforecastapp.common.Constants

import com.yusuferkamozyer.weatherforecastapp.presentation.Screen
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.AppColors
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.components.EditableDialog
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view_model.WeatherForecastViewModel


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WeatherForecastScreen(
    viewModel: WeatherForecastViewModel = hiltViewModel(),
    navController: NavController,
) {
    val locationInfo = viewModel.locationInfo.value
    val state = viewModel.state.value
    val api_key = Constants.api_key
    val location by viewModel.location.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    locationInfo.localInformationModel?.let{
                        if (locationInfo.localInformationModel.cityDistrict != "Unknown District") {
                            Text(locationInfo.localInformationModel.city + "/" + locationInfo.localInformationModel.cityDistrict)
                        }
                        else{
                            Text(locationInfo.localInformationModel.city)
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.SettingsScreen.route) }) {
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
    ) { values ->
        LaunchedEffect(Unit) {
            viewModel.fetchLocation()
        }
        LaunchedEffect(location) {
            location?.let {
                viewModel.getWeatherForecast(it.latitude, it.longitude, "", api_key)
                viewModel.getLocationInformation("${it.latitude},${it.longitude}")
            } ?: println("Konum bilgisi henüz alınmadı")
        }
        val onRefresh = {
            isRefreshing = true
            location?.let {
                viewModel.getWeatherForecast(it.latitude, it.longitude, "", api_key)
                viewModel.getLocationInformation("${it.latitude},${it.longitude}")
            }
            isRefreshing = false
        }
        state.weatherForecastModel?.let { weatherForecastModel ->
            locationInfo.localInformationModel?.let {
                PullToRefreshWeatherColumn(
                    weatherForecastModel = weatherForecastModel,
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values),
                    navController = navController,
                    locationInformationState = locationInfo
                )
            }
        }
        if (locationInfo.error.isNotBlank()) {
            println(locationInfo.error)
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








