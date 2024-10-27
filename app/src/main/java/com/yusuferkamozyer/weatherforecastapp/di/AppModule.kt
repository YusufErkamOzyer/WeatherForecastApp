package com.yusuferkamozyer.weatherforecastapp.di

import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.data.remote.OpenWeatherApi
import com.yusuferkamozyer.weatherforecastapp.data.repository.WeatherForecastRepositoryImpl
import com.yusuferkamozyer.weatherforecastapp.domain.repository.WeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    @Singleton
    @Provides
    fun provideWeatherForecastRepository(api:OpenWeatherApi):WeatherForecastRepository{
        return WeatherForecastRepositoryImpl(api)
    }
}