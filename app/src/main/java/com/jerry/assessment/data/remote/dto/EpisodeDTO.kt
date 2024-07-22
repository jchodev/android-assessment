package com.jerry.assessment.data.remote.dto

import com.squareup.moshi.Json

//There have not schemas on swagger level, therefore we have to handle null case
data class EpisodeDTO(
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "duration") val duration: Long? = 0,

    @field:Json(name = "id") val id: Long? = null,
    @field:Json(name = "mimeType") val mimeType: String? = null,
    @field:Json(name = "published") val published: Long? = null,

    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "type") val type: String? = null,
    @field:Json(name = "url") val url: String? = null,
)