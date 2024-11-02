package com.yusuferkamozyer.weatherforecastapp.domain.use_case

import com.yusuferkamozyer.weatherforecastapp.common.Resource
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.WeatherForecastDTO
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.toWeatherForecastModel
import com.yusuferkamozyer.weatherforecastapp.domain.model.WeatherForecastModel
import com.yusuferkamozyer.weatherforecastapp.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(private val repository: WeatherForecastRepository) {
    operator fun invoke(latitude: Double, longitude: Double, exclude: String, apiKey: String): Flow<Resource<WeatherForecastModel>> =flow{
        try {
            emit(Resource.Loading())
            val response=repository.getWeatherForecast(latitude, longitude, exclude, apiKey)
            emit(Resource.Success(response.toWeatherForecastModel()))
        }catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occured"))
        }
    }

}