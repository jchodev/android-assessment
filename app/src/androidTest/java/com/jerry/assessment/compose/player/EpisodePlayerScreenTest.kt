package com.jerry.assessment.compose.player

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jerry.assessment.MockStubs
import com.jerry.assessment.contants.TEST_TAG_TITLE
import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState
import org.junit.Rule
import org.junit.Test

class EpisodePlayerScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithLoadedItem() {
        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode().copy(
            podcast = MockStubs.mockPodcastDTOS[0].toPodcast()
        )
        val displayEpisode = MockStubs.mockEpisodeDTOS[1].toEpisode()
        //assign
        rule.setContent {
            AssessmentprojectTheme {
                EpisodePlayerScreen(
                    selectedEpisode = displayEpisode,
                    episodePlayerState = EpisodePlayerState(
                        episode = episode
                    ),
                    onBackClick = {},
                    onPlayPause = {},
                    onPlayVideoClick = {},
                    onErrorDismissRequest = {},
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_TITLE
        ).assertExists(
            displayEpisode.title
        )
    }


    @Test
    fun testWithError() {
        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode().copy(
            podcast = MockStubs.mockPodcastDTOS[0].toPodcast()
        )
        val displayEpisode = MockStubs.mockEpisodeDTOS[1].toEpisode()
        //assign
        rule.setContent {
            AssessmentprojectTheme {
                EpisodePlayerScreen(
                    selectedEpisode = displayEpisode,
                    episodePlayerState = EpisodePlayerState(
                        episode = episode,
                        errorMessage = MockStubs.mockExceptionStr
                    ),
                    onBackClick = {},
                    onPlayPause = {},
                    onPlayVideoClick = {},
                    onErrorDismissRequest = {},
                )
            }
        }

        //check
        rule.onNodeWithText(MockStubs.mockExceptionStr).assertExists()
    }
}