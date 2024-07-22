package com.jerry.assessment.data.repository

import com.jerry.assessment.MockStubs
import com.jerry.assessment.data.remote.response.BasePaginationResponse
import com.jerry.assessment.data.remote.response.ErrorResponse
import com.jerry.assessment.network.EpisodeApi
import com.jerry.assessment.utilities.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkClass
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
@MockKExtension.ConfirmVerification
class EpisodeRepositoryTest {
    private lateinit var retrofit: Retrofit
    private lateinit var repository: EpisodeRepository
    private lateinit var episodeApi: EpisodeApi

    @MockK
    private lateinit var errorResponseConverter : Converter<ResponseBody, ErrorResponse>

    @BeforeEach
    fun setUp() {
        retrofit = mockkClass(Retrofit::class)
        episodeApi = mockkClass(EpisodeApi::class)

        repository = EpisodeRepository(
            retrofit = retrofit,
            episodeApi = episodeApi,
        )

        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test EpisodeRepository getPodcastsByPodcastId return success`() {
        val successResponse = BasePaginationResponse(
            results = MockStubs.mockEpisodeDTOS
        )

        runTest {
            //assign
            coEvery { episodeApi.getPodcastsByPodcastId(any()) } returns Response.success(successResponse)

            //action
            val actual = repository.getPodcastsByPodcastId(1).first()

            //verify
            Assertions.assertTrue(
                actual is Resource.Success
            )
            val successResult = (actual as Resource.Success)
            Assertions.assertEquals(true, successResult.data.isNotEmpty() )
            Assertions.assertEquals(successResponse.results.size, successResult.data.size)
        }
    }

    @Test
    fun `test PodcastRepository getPodcastsByPodcastId return catch`() {
        runTest {
            //assign
            coEvery { episodeApi.getPodcastsByPodcastId(any()) } throws MockStubs.mockNormalException

            //action
            val actual = repository.getPodcastsByPodcastId(1).first()

            //verify
            Assertions.assertTrue(
                actual is Resource.Error
            )
        }
    }

    @Test
    fun `test PodcastRepository getPodcastsByPodcastId return error from server`() = runTest {
        coEvery { retrofit.responseBodyConverter<ErrorResponse>(any(), any()) } returns errorResponseConverter
        coEvery { errorResponseConverter.convert(any()) } returns ErrorResponse(
            message = MockStubs.mockExceptionStr
        )

        //assign
        coEvery { episodeApi.getPodcastsByPodcastId(any()) } returns Response.error(
            MockStubs.mockServerErrorCode,
            ResponseBody.create("text/plain".toMediaTypeOrNull(), "msg")
        )

        //action
        val actual = repository.getPodcastsByPodcastId(1).first()

        //verify
        Assertions.assertTrue(
            actual is Resource.Error
        )

        Assertions.assertEquals(
            MockStubs.mockExceptionStr,
            (actual as Resource.Error<ErrorResponse>).message
        )
    }
}