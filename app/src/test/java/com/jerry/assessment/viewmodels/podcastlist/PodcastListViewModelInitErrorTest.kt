package com.jerry.assessment.viewmodels.podcastlist

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import app.cash.turbine.test
import com.jerry.assessment.MockStubs
import com.jerry.assessment.MockStubs.Companion.mockErrorPodcastListResourceDTO
import io.mockk.coEvery
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions

class PodcastListViewModelInitErrorTest: BasePodcastListViewModelTest() {

    override fun assignInitResult() {
        coEvery { podcastRepository.getTopList() } returns flowOf(
            mockErrorPodcastListResourceDTO
        )
    }

    @Test
    fun `test PodcastListViewModel podcastListState when init expected error`() = runTest {
        viewModel.podcastListState.test{
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