package com.yusuferkamozyer.weatherforecastapp.presentation

import AppTheme
import RequestLocationPermissionScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.WeatherForecastScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
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
