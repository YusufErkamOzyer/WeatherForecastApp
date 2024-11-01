package com.yusuferkamozyer.weatherforecastapp.presentation.weather_forecast.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingsMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        // Menü butonu
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menüyü aç"
            )
        }

        // DropdownMenu bileşeni
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                // İlk menü öğesine tıklandığında yapılacak işlem
                expanded = false
                println("Öğe 1 seçildi")
            }) {
                Text("Öğe 1")
            }
            DropdownMenuItem(onClick = {
                expanded = false
                println("Öğe 2 seçildi")
            }) {
                Text("Öğe 2")
            }
            DropdownMenuItem(onClick = {
                expanded = false
                println("Öğe 3 seçildi")
            }) {
                Text("Öğe 3")
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}
