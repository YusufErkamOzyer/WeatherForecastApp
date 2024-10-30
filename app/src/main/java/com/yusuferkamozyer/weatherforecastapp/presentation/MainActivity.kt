package com.yusuferkamozyer.weatherforecastapp.presentation

import RequestLocationPermissionScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yusuferkamozyer.weatherforecastapp.presentation.theme.WeatherForecastAppTheme
import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.WeatherForecastScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.RequestLocationPermissionScreen.route
                ) {
                    composable(route = Screen.RequestLocationPermissionScreen.route) {
                        RequestLocationPermissionScreen(navController)
                    }
                    composable(route = Screen.WeatherForecastScreen.route) {
                        WeatherForecastScreen()
                    }
                }
            }
        }
    }

}
