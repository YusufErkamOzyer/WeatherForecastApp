package com.yusuferkamozyer.weatherforecastapp.presentation

import AppTheme
import RequestLocationPermissionScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yusuferkamozyer.weatherforecastapp.presentation.settings.view.SettingsScreen

import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.WeatherForecastScreen
import com.yusuferkamozyer.weatherforecastapp.presentation.week_weather_forecast.view.WeekWeatherForecastScreen
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
                        WeatherForecastScreen(navController = navController)
                    }
                    composable(route = "${Screen.WeekWeatherForecastScreen.route}/{lat}/{lon}",
                        arguments = listOf(
                            navArgument("lat") { type = NavType.StringType },
                            navArgument("lon") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val latString = backStackEntry.arguments?.getString("lat") ?: "0.0"
                        val lonString = backStackEntry.arguments?.getString("lon") ?: "0.0"
                        val lat = latString.toDouble()
                        val lon = lonString.toDouble()
                        WeekWeatherForecastScreen(
                            navController = navController,
                            lat = lat,
                            lon = lon
                        )
                    }
                    composable(
                        route = Screen.SettingsScreen.route
                    ) {
                        SettingsScreen(navController = navController)
                    }
                }
            }
        }
    }
}
