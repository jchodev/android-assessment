package com.jerry.assessment.viewmodels.podcastlist

import com.jerry.assessment.MockStubs.Companion.mockSuccessPodcastListResourceDTO
import com.jerry.assessment.data.repository.PodcastRepository
import com.jerry.assessment.viewmodels.PodcastListViewModel
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
open class BasePodcastListViewModelTest {

    lateinit var viewModel: PodcastListViewModel
    lateinit var podcastRepository: PodcastRepository

    @BeforeEach
    open fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        podcastRepository = mockkClass(PodcastRepository::class)
        mockkStatic(Timber::class)

        assignInitResult()

        viewModel = PodcastListViewModel(
            podcastRepository = podcastRepository,
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    open fun assignInitResult(){
        coEvery { podcastRepository.getTopList() } returns flowOf(
            mockSuccessPodcastListResourceDTO
        )

    }
}