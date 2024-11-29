package com.yusuferkamozyer.weatherforecastapp.domain.use_case

import com.yusuferkamozyer.weatherforecastapp.common.Resource
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.geocodingdto.toLocalInformationModel
import com.yusuferkamozyer.weatherforecastapp.domain.model.LocalInformationModel
import com.yusuferkamozyer.weatherforecastapp.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetLocalInformationUseCase @Inject constructor(
    private val repository: WeatherForecastRepository
) {
    operator fun invoke(latlon: String): Flow<Resource<LocalInformationModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getLocalInformation(latlon)
            emit(Resource.Success(response.toLocalInformationModel()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}