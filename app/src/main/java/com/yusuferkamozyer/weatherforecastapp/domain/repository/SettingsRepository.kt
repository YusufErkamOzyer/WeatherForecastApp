package com.yusuferkamozyer.weatherforecastapp.domain.repository

import com.yusuferkamozyer.weatherforecastapp.domain.model.SettingsState
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settingsStateFlow: Flow<SettingsState>
    suspend fun updateIsCelsius(isCelsius: Boolean)
}