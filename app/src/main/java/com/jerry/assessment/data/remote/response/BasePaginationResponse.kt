package com.jerry.assessment.data.remote.response

import com.squareup.moshi.Json

data class BasePaginationResponse<T>(
    @field:Json(name = "results") val results: List<T> = emptyList(),
    @field:Json(name = "limit") val limit: Int = 0,
    @field:Json(name = "offset") val offset: Int = 0,
    @field:Json(name = "total") val total: Int = 0,
)