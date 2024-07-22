package com.jerry.assessment.data.repository

import com.jerry.assessment.base.BaseRepository
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.utilities.Resource
import com.jerry.assessment.data.remote.dto.PodcastDTO
import com.jerry.assessment.data.remote.response.ErrorResponse
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.network.PodcastApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastRepository @Inject constructor(
    private val retrofit: Retrofit,
    private val podcastApi: PodcastApi,
): BaseRepository() {

    suspend fun getTopList(): Flow<Resource<List<Podcast>>> = flow {
        val response = podcastApi.getTopList()
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
                        it.toPodcast()
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