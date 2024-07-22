package com.jerry.assessment.compose.podcastdetail

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jerry.assessment.MockStubs
import com.jerry.assessment.R
import com.jerry.assessment.compose.podcastlist.PodcastListScreen
import com.jerry.assessment.contants.TEST_TAG_LOADING
import com.jerry.assessment.contants.TEST_TAG_TITLE

import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.PodcastDetailState
import org.junit.Rule
import org.junit.Test

class PodcastDetailScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithLoadedItem() {

        val podcast = MockStubs.mockPodcastDTOS[0].toPodcast()
        val episodes = MockStubs.mockEpisodeDTOS.map { it.toEpisode() }

        //assign
        rule.setContent {
            AssessmentprojectTheme {
                PodcastDetailScreen (
                    podcastDetailState = PodcastDetailState().copy(
                        data = episodes
                    ),
                    selectedPodcast = podcast,
                    onBackClick = {},
                    onRefresh = {},
                    onEpisodeClick = {},
                    onErrorDismissRequest = {},
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_TITLE
        ).assertExists(
            podcast.title
        )
    }

    @Test
    fun testWithError() {

        val podcast = MockStubs.mockPodcastDTOS[0].toPodcast()

        //assign
        rule.setContent {
            PodcastDetailScreen (
                podcastDetailState = PodcastDetailState().copy(
                    errorMessage = MockStubs.mockExceptionStr
                ),
                selectedPodcast = podcast,
                onBackClick = {},
                onRefresh = {},
                onEpisodeClick = {},
                onErrorDismissRequest = {},
            )
        }

        //check
        rule.onNodeWithText(MockStubs.mockExceptionStr).assertExists()
    }


    @Test
    fun testWithLoading() {
        //assign
        rule.setContent {
            PodcastDetailScreen (
                podcastDetailState = PodcastDetailState().copy(
                    isLoading = true
                ),
                selectedPodcast = MockStubs.mockPodcastDTOS[0].toPodcast(),
                onBackClick = {},
                onRefresh = {},
                onEpisodeClick = {},
                onErrorDismissRequest = {},
            )
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_LOADING
        ).assertExists()
    }
}