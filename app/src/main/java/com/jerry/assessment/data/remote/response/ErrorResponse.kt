package com.jerry.assessment.data.remote.response

import com.squareup.moshi.Json

data class ErrorResponse(
    @field:Json(name = "message") val message: String?
)