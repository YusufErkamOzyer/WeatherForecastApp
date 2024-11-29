package com.yusuferkamozyer.weatherforecastapp.data.remote.dto.geocodingdto

import com.yusuferkamozyer.weatherforecastapp.domain.model.LocalInformationModel

data class LocalInformationDTO(
    val documentation: String,
    val licenses: List<License>,
    val rate: Rate,
    val results: List<Result>,
    val status: Status,
    val stay_informed: Stayİnformed,
    val thanks: String,
    val timestamp: Timestamp,
    val total_results: Int
)


fun LocalInformationDTO.toLocalInformationModel(): LocalInformationModel {
    val firstResult = results.getOrNull(0)
    return LocalInformationModel(
        city = firstResult?.components?.city ?: "Unknown City",
        cityDistrict = firstResult?.components?.city_district ?: "Unknown District"
    )
}