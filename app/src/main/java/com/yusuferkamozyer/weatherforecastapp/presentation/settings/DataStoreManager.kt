package com.yusuferkamozyer.weatherforecastapp.presentation.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yusuferkamozyer.weatherforecastapp.domain.model.SettingsState
import kotlinx.coroutines.flow.map

const val SETTINGS_DATASTORE="SETTINGS_DATA"
val Context.preferenceDataStore:DataStore<Preferences> by preferencesDataStore(name=SETTINGS_DATASTORE)
class DataStoreManager(val context: Context) {
    companion object{
        val is_celsius= booleanPreferencesKey("is_celsius")
        val language= stringPreferencesKey("language")
    }
    suspend fun saveToDataStore(settingsState: SettingsState){
        context.preferenceDataStore.edit {
            it[is_celsius]=settingsState.isCelsius
            it[language]=settingsState.language
        }
    }

    fun getDataFromDataStore()=context.preferenceDataStore.data.map {
        SettingsState(
            isCelsius = it[is_celsius]?:true,
            language = it[language]?:""
        )
    }
}