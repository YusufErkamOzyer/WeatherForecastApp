package com.yusuferkamozyer.weatherforecastapp.data.remote

import com.yusuferkamozyer.weatherforecastapp.common.Constants
import com.yusuferkamozyer.weatherforecastapp.data.remote.dto.geocodingdto.LocalInformationDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenCageGeocodingApi {
    @GET("geocode/v1/json")
    suspend fun getLocalName(
        @Query("q") q: String,
        @Query("key") key: String=Constants.api_local_key
    ):LocalInformationDTO
}