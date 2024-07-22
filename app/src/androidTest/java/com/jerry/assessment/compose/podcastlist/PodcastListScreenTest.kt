package com.jerry.assessment.compose.podcastlist

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jerry.assessment.MockStubs
import com.jerry.assessment.R
import com.jerry.assessment.contants.TEST_TAG_LOADING
import com.jerry.assessment.contants.TEST_TAG_TITLE
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.PodcastListState
import org.junit.Rule
import org.junit.Test

class PodcastListScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithLoadedItem() {

        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val podcastList = MockStubs.mockPodcastDTOS.map { it.toPodcast() }

        //assign
        rule.setContent {
            AssessmentprojectTheme {
                PodcastListScreen (
                    podcastListState = PodcastListState().copy(
                        data = podcastList
                    ),
                    onPodcastClick = {},
                    onRefresh = {},
                    onErrorDismissRequest = {}
                )
            }
        }

        //check
        rule.onNodeWithText(podcastList[0].title).assertExists()
        //check
        rule.onNodeWithTag(
            TEST_TAG_TITLE
        ).assertExists(
            context.getString(R.string.podcast_list_title)
        )
    }

    @Test
    fun testWithError() {

        //assign
        rule.setContent {
            PodcastListScreen (
                podcastListState = PodcastListState().copy(
                    errorMessage = MockStubs.mockExceptionStr
                ),
                onPodcastClick = {},
                onRefresh = {},
                onErrorDismissRequest = {}
            )
        }

        //check
        rule.onNodeWithText(MockStubs.mockExceptionStr).assertExists()
    }


    @Test
    fun testWithLoading() {
        //assign
        rule.setContent {
            PodcastListScreen (
                podcastListState = PodcastListState().copy(
                    isLoading = true
                ),
                onPodcastClick = {},
                onRefresh = {},
                onErrorDismissRequest = {}
            )
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_LOADING
        ).assertExists()
    }
}