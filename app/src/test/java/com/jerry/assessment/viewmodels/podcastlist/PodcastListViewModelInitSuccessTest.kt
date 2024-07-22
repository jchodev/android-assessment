package com.jerry.assessment.viewmodels.podcastlist

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import app.cash.turbine.test
import com.jerry.assessment.MockStubs
import org.junit.jupiter.api.Assertions

class PodcastListViewModelInitSuccessTest: BasePodcastListViewModelTest() {

    @Test
    fun `test PodcastListViewModel podcastListState when init collect expected success`() = runTest {
        viewModel.podcastListState.test{
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
            Assertions.assertEquals(MockStubs.mockPodcastDTOS.size, successData.size)

            val finalItem = awaitItem()
            Assertions.assertEquals(false, finalItem.isLoading)
            Assertions.assertEquals(null, finalItem.errorMessage)
            Assertions.assertEquals(MockStubs.mockPodcastDTOS.size, finalItem.data.size)
        }
    }
}