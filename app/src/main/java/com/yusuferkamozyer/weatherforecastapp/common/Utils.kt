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
    fun formatUtcToTimeString(utcMillis: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC") // Zaman dilimini UTC olarak ayarlayın
        return dateFormat.format(Date(utcMillis*1000))
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayFromUtcMillis(utcMillis: Long): String {
        val instant = Instant.ofEpochMilli(utcMillis*1000)
        val dateTime = instant.atZone(ZoneId.of("UTC"))
        val formatter = DateTimeFormatter.ofPattern("EEEE") // Gün ismini verir, örn. "Monday"
        return dateTime.format(formatter)
    }


}