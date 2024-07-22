package com.jerry.assessment.compose.player

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.test.platform.app.InstrumentationRegistry
import com.jerry.assessment.MockStubs
import com.jerry.assessment.contants.TEST_TAG_LOADING
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState
import org.junit.Rule
import org.junit.Test

class VideoFullScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testWithLoadingStatus() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        //assign
        rule.setContent {
            AssessmentprojectTheme {
                VideoFullScreen (
                    episodePlayerState = EpisodePlayerState().copy(
                        playbackState = Player.STATE_BUFFERING,
                    ),
                    onRetry = {},
                    onCancel = {},
                    exoPlayer = ExoPlayer.Builder(context).build(),
                )
            }
        }

        //check
        rule.onNodeWithTag(
            TEST_TAG_LOADING
        ).assertExists()
    }

    @Test
    fun testWithErrorStatus() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        //assign
        rule.setContent {
            AssessmentprojectTheme {
                VideoFullScreen (
                    episodePlayerState = EpisodePlayerState().copy(
                        playbackState = Player.STATE_IDLE,
                        errorMessage = MockStubs.mockExceptionStr
                    ),
                    onRetry = {},
                    onCancel = {},
                    exoPlayer = ExoPlayer.Builder(context).build(),
                )
            }
        }

        //check
        rule.onNodeWithText(MockStubs.mockExceptionStr).assertExists()
    }
}