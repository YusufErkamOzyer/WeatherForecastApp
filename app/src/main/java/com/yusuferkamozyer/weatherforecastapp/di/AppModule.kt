package com.yusuferkamozyer.weatherforecastapp.di

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.data.local.DefaultLocationTracker
import com.yusuferkamozyer.weatherforecastapp.data.local.LocationTracker
import com.yusuferkamozyer.weatherforecastapp.data.remote.OpenCageGeocodingApi
import com.yusuferkamozyer.weatherforecastapp.data.remote.OpenWeatherApi
import com.yusuferkamozyer.weatherforecastapp.data.repository.WeatherForecastRepositoryImpl
import com.yusuferkamozyer.weatherforecastapp.domain.repository.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): OpenWeatherApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeocodingApi(): OpenCageGeocodingApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_LOCAL_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(OpenCageGeocodingApi::class.java)
    }


    @Singleton
    @Provides
    fun provideWeatherForecastRepository(
        api: OpenWeatherApi,
        api_geo: OpenCageGeocodingApi

    ): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(api, api_geo)
    }


    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(
        @ApplicationContext context: Context,
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Provides
    @Singleton
    fun providesLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        application: Application,
    ): LocationTracker = DefaultLocationTracker(
        fusedLocationProviderClient = fusedLocationProviderClient,
        application = application
    )
}