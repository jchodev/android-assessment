package com.jerry.assessment.network

import com.jerry.assessment.data.remote.dto.EpisodeDTO
import com.jerry.assessment.data.remote.response.BasePaginationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("/v1/podcasts/{podcastId}/episodes")
    suspend fun getPodcastsByPodcastId(
        @Path("podcastId") podcastId: Long
    ): retrofit2.Response<BasePaginationResponse<EpisodeDTO>>
}