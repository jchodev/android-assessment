package com.jerry.assessment.data.remote.dto

import com.squareup.moshi.Json

data class PodcastDTO(
    @field:Json(name = "author") val author: String = "",
    @field:Json(name = "description") val description: String = "",
    @field:Json(name = "id") val id: Long = 0,
    @field:Json(name = "image") val image: String? = null,
    @field:Json(name = "link") val link: String? = null,
    @field:Json(name = "title") val title: String
)