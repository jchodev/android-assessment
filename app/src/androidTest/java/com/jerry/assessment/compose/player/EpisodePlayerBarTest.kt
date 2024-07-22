package com.jerry.assessment.compose.player

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jerry.assessment.MockStubs
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_BAR_ERROR
import com.jerry.assessment.contants.TEST_TAG_TITLE
import com.jerry.assessment.data.toEpisode
import com.jerry.assessment.data.toPodcast
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState
import org.junit.Rule
import org.junit.Test

class EpisodePlayerBarTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithLoadedItem() {
        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode().copy(
            podcast = MockStubs.mockPodcastDTOS[0].toPodcast()
        )

        //assign
        rule.setContent {
            AssessmentprojectTheme {
                EpisodePlayerBar(
                    episodePlayerState = EpisodePlayerState(
                        episode = episode
                    ),
                    onPlayPauseClick = {},
                )
            }
        }

        //check
        rule.onNodeWithText(episode.description).assertExists()
    }

    @Test
    fun testWithErrorState() {
        val episode = MockStubs.mockEpisodeDTOS[0].toEpisode().copy(
            podcast = MockStubs.mockPodcastDTOS[0].toPodcast()
        )

        //assign
        rule.setContent {
            AssessmentprojectTheme {
                EpisodePlayerBar(
                    episodePlayerState = EpisodePlayerState(
                        episode = episode,
                        errorMessage = MockStubs.mockExceptionStr
                    ),
                    onPlayPauseClick = {},
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_PLAYBACK_BAR_ERROR, useUnmergedTree = true
        ).assertExists()
    }
}