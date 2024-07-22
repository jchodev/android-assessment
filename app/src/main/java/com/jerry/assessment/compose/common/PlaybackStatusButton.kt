package com.jerry.assessment.compose.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import com.jerry.assessment.R
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_LOADING_DISPLAYED
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_PAUSE_DISPLAYED
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_PLAY_DISPLAYED
import com.jerry.assessment.ui.theme.AssessmentprojectTheme


@Composable
fun PlaybackStatusButton(
    playbackState: Int = Player.STATE_IDLE,
    isPlaying: Boolean = false,
    onClick: () -> Unit = {},
    size: Dp = 50.dp,
    episodeTitle : String = "",
){
    IconButton(
        modifier =Modifier
            .testTag(
                if (playbackState == Player.STATE_BUFFERING){
                    TEST_TAG_PLAYBACK_LOADING_DISPLAYED
                } else if (isPlaying) {
                    TEST_TAG_PLAYBACK_PAUSE_DISPLAYED
                } else {
                    TEST_TAG_PLAYBACK_PLAY_DISPLAYED
                }
            )
            .size(size),
        onClick = {
            onClick()
        }) {
        if (playbackState == Player.STATE_BUFFERING){
            CircularProgressIndicator()
        } else {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                imageVector = if (isPlaying) {
                    Icons.Default.PauseCircle
                } else {
                    Icons.Default.PlayCircle
                },
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = if (isPlaying) {
                    stringResource(id = R.string.pause, episodeTitle)
                } else {
                    stringResource(id = R.string.play, episodeTitle)
                }
            )
        }

    }
}

@DevicePreviews
@Composable
private fun PlaybackStatusButtonLoadingPreview(){
    AssessmentprojectTheme {
        PlaybackStatusButton(
            playbackState = Player.STATE_BUFFERING
        )
    }
}

@DevicePreviews
@Composable
private fun PlaybackStatusButtonPlayingAudioPreview(){
    AssessmentprojectTheme {
        PlaybackStatusButton(
            playbackState = Player.STATE_READY,
            isPlaying = true,
        )
    }
}

@DevicePreviews
@Composable
private fun PlaybackStatusButtonPausAudioPreview(){
    AssessmentprojectTheme {
        PlaybackStatusButton(
            playbackState = Player.STATE_READY,
            isPlaying = false,
        )
    }
}

