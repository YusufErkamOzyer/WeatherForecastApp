package com.yusuferkamozyer.weatherforecastapp.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.TimeZone

object Utils {
    fun kelvinToCelsius(kelvin: Double): String {
        val celsius = kelvin - 273.15
        return String.format("%.1f", celsius)
    }
    fun urlCreator(iconId:String):String="https://openweathermap.org/img/wn/${iconId}@2x.png"
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatUtcToTimeString(unixTime: Long): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.of("UTC"))
        return formatter.format(Instant.ofEpochSecond(unixTime))
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayFromUtcMillis(utcMillis: Long): String {
        val instant = Instant.ofEpochMilli(utcMillis*1000)
        val dateTime = instant.atZone(ZoneId.of("UTC"))
        val formatter = DateTimeFormatter.ofPattern("EEEE") // Gün ismini verir, örn. "Monday"
        return dateTime.format(formatter)
    }


}