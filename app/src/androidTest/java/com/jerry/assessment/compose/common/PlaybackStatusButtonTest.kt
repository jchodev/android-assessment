package com.jerry.assessment.compose.common

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.media3.common.Player
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_LOADING_DISPLAYED
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_PAUSE_DISPLAYED
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_PLAY_DISPLAYED
import com.jerry.assessment.contants.TEST_TAG_TITLE
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import org.junit.Rule
import org.junit.Test

class PlaybackStatusButtonTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithLoadingStatus() {
        //assign
        rule.setContent {
            AssessmentprojectTheme {
                PlaybackStatusButton(
                    playbackState = Player.STATE_BUFFERING
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_PLAYBACK_LOADING_DISPLAYED
        ).assertExists()
    }

    @Test
    fun testWithPlayingStatus() {
        //assign
        rule.setContent {
            AssessmentprojectTheme {
                PlaybackStatusButton(
                    playbackState = Player.STATE_READY,
                    isPlaying = true
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_PLAYBACK_PAUSE_DISPLAYED
        ).assertExists()
    }

    @Test
    fun testWithPauseStatus() {
        //assign
        rule.setContent {
            AssessmentprojectTheme {
                PlaybackStatusButton(
                    playbackState = Player.STATE_READY,
                    isPlaying = false
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_PLAYBACK_PLAY_DISPLAYED
        ).assertExists()
    }
}