package com.jerry.assessment.viewmodels.podcastdetail

import app.cash.turbine.test
import com.jerry.assessment.MockStubs
import com.jerry.assessment.MockStubs.Companion.mockErrorEpisodeListResourceDTO
import com.jerry.assessment.MockStubs.Companion.mockErrorPodcastListResourceDTO
import com.jerry.assessment.MockStubs.Companion.mockSuccessEpisodeListResourceDTO
import com.jerry.assessment.data.repository.EpisodeRepository
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.viewmodels.PodcastDetailViewModel
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
class PodcastDetailViewModelTest {

    private lateinit var viewModel: PodcastDetailViewModel
    private lateinit var episodeRepository: EpisodeRepository

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        episodeRepository = mockkClass(EpisodeRepository::class)
        mockkStatic(Timber::class)

        viewModel = PodcastDetailViewModel(
            episodeRepository = episodeRepository,
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `test EpisodeRepository getPodcastsByPodcastId expected success`() = runTest {
        //assign
        coEvery { episodeRepository.getPodcastsByPodcastId(any()) } returns flowOf(
            mockSuccessEpisodeListResourceDTO
        )

        //action
        viewModel.getPodcastsByPodcast(MockStubs.mockPodcastDTOS[1].toPodcast())

        viewModel.podcastDetailState.test {

            //verify Initial Item
            val initialItem = awaitItem()
            Assertions.assertEquals(false, initialItem.isLoading)
            Assertions.assertEquals(null, initialItem.errorMessage)
            Assertions.assertEquals(true, initialItem.data.isEmpty())

            //verify loading
            val loadingItem = awaitItem()
            Assertions.assertEquals(true, loadingItem.isLoading)
            Assertions.assertEquals(null, loadingItem.errorMessage)
            Assertions.assertEquals(true, loadingItem.data.isEmpty())

            //verify - success
            val successItem = awaitItem()
            val successData = successItem.data
            Assertions.assertEquals(true, successItem.isLoading)
            Assertions.assertEquals(null, successItem.errorMessage)
            Assertions.assertEquals(MockStubs.mockEpisodeDTOS.size, successData.size)

            val finalItem = awaitItem()
            Assertions.assertEquals(false, finalItem.isLoading)
            Assertions.assertEquals(null, finalItem.errorMessage)
            Assertions.assertEquals(MockStubs.mockEpisodeDTOS.size, finalItem.data.size)
        }
    }

    @Test
    fun `test EpisodeRepository getPodcastsByPodcastId expected error`() = runTest {
        //assign
        coEvery { episodeRepository.getPodcastsByPodcastId(any()) } returns flowOf(
            mockErrorEpisodeListResourceDTO
        )

        //action
        viewModel.getPodcastsByPodcast(MockStubs.mockPodcastDTOS[1].toPodcast())

        viewModel.podcastDetailState.test {

            //verify Initial Item
            val initialItem = awaitItem()
            Assertions.assertEquals(false, initialItem.isLoading)
            Assertions.assertEquals(null, initialItem.errorMessage)
            Assertions.assertEquals(true, initialItem.data.isEmpty())

            //verify loading
            val loadingItem = awaitItem()
            Assertions.assertEquals(true, loadingItem.isLoading)
            Assertions.assertEquals(null, loadingItem.errorMessage)
            Assertions.assertEquals(true, loadingItem.data.isEmpty())

            //verify - error
            val errorItem = awaitItem()
            val errorMessage = errorItem.errorMessage
            Assertions.assertEquals(true, errorItem.isLoading)
            Assertions.assertEquals(true, errorItem.errorMessage != null)
            Assertions.assertEquals(MockStubs.mockExceptionStr, errorMessage)

            val finalItem = awaitItem()
            Assertions.assertEquals(false, finalItem.isLoading)
            Assertions.assertEquals(true, finalItem.errorMessage != null)
        }
    }
}