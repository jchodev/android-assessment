package com.jerry.assessment.data.repository

import com.jerry.assessment.base.BaseRepository
import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.remote.response.ErrorResponse
import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.network.EpisodeApi
import com.jerry.assessment.utilities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class EpisodeRepository @Inject constructor(
    private val retrofit: Retrofit,
    private val episodeApi: EpisodeApi,
): BaseRepository() {

    suspend fun getPodcastsByPodcastId(podcastId: Long): Flow<Resource<List<Episode>>> = flow {
        val response = episodeApi.getPodcastsByPodcastId(podcastId)
        val serverError = response.errorBody()?.let {
            getErrorBodyAs(
                errorBody = it,
                retrofit = retrofit,
                type = ErrorResponse::class.java
            )
        }
        if (serverError?.message != null) {
            emit(
                Resource.Error(serverError.message)
            )
        } else if (response.body() != null && response.body()!!.results.isNotEmpty()) {
            emit(
                Resource.Success(
                    response.body()!!.results.map {
                        it.toEpisode()
                    }
                )
            )
        } else {
            emit(
                Resource.Error(response.message())
            )
        }
    }.catch {
        emit(
            Resource.Error(it.localizedMessage ?: it.message ?: "UnKnown Error")
        )
    }
}