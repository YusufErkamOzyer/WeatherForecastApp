package com.yusuferkamozyer.weatherforecastapp.data.local

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}