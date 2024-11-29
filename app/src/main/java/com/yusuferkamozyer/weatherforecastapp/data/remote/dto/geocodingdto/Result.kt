package com.yusuferkamozyer.weatherforecastapp.data.remote.dto.geocodingdto

data class Result(
    val bounds: Bounds,
    val components: Components,
    val confidence: Int,
    val distance_from_q: DistanceFromQ,
    val formatted: String,
    val geometry: Geometry
)