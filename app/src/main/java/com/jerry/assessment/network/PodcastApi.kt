package com.jerry.assessment.network


import com.jerry.assessment.data.remote.dto.PodcastDTO
import com.jerry.assessment.data.remote.response.BasePaginationResponse
import retrofit2.http.GET

interface PodcastApi {

    @GET("v1/toplist")
    suspend fun getTopList(): retrofit2.Response<BasePaginationResponse<PodcastDTO>>

}