@file:OptIn(ExperimentalMaterial3Api::class)

package com.plcoding.pulltorefreshcompose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel

import com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view.WeatherForecastScreenView
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshWeatherColumn(
    weatherForecastModel: WeatherForecastModel,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    navController: NavController
) {
    val pullToRefreshState = rememberPullToRefreshState()
    Box(
        modifier = modifier
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        WeatherForecastScreenView(weatherForecastModel,lazyListState,navController)
        if (pullToRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                onRefresh()
            }
        }

        LaunchedEffect(isRefreshing) {
            if (isRefreshing) {
                pullToRefreshState.startRefresh()
            } else {
                pullToRefreshState.endRefresh()
            }
        }

        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
