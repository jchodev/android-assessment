package com.jerry.assessment.compose.player


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.jerry.assessment.compose.common.ErrorDialog
import com.jerry.assessment.compose.common.LoadingCompose
import com.jerry.assessment.compose.preview.DevicePreviews

import com.jerry.assessment.data.Episode
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState

import com.jerry.assessment.viewmodels.EpisodePlayerViewModel

@Composable
fun VideoFullScreen(
    episodePlayerViewModel: EpisodePlayerViewModel =  hiltViewModel(),
    episode: Episode,
    exoPlayer: ExoPlayer,
    onCancel: () -> Unit,
) {
    val episodePlayerState = episodePlayerViewModel.episodePlayerState.collectAsStateWithLifecycle().value

    VideoFullScreen(
        episodePlayerState = episodePlayerState,
        onRetry = {
            episodePlayerViewModel.playPause(
                episode = episode
            )
        },
        onCancel = onCancel,
        exoPlayer = exoPlayer
    )

    LaunchedEffect(episode) {
        episodePlayerViewModel.playPause(
            episode = episode
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            episodePlayerViewModel.clearEpisode()
        }
    }
}

@Composable
fun VideoFullScreen(
    episodePlayerState: EpisodePlayerState,
    onRetry: () -> Unit,
    onCancel: () -> Unit,
    exoPlayer: ExoPlayer,
) {
    Box(modifier = Modifier.fillMaxSize()){
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )

        if (episodePlayerState.playbackState == Player.STATE_BUFFERING) {
            LoadingCompose()
        }

        episodePlayerState.errorMessage?.let {
            ErrorDialog(
                text = it,
                onRetryRequest = onRetry,
                onDismissRequest = onCancel,
            )
        }
    }
}

@DevicePreviews
@Composable
private fun VideoFullScreenPreview(){
    val context = LocalContext.current
    AssessmentprojectTheme {
        VideoFullScreen(
            exoPlayer = ExoPlayer.Builder(context).build(),
            onRetry = {},
            onCancel = {},
            episodePlayerState = EpisodePlayerState()
        )
    }
}

@DevicePreviews
@Composable
private fun VideoFullScreenErrorPreview(){
    val context = LocalContext.current
    AssessmentprojectTheme {
        VideoFullScreen(
            exoPlayer = ExoPlayer.Builder(context).build(),
            onRetry = {},
            onCancel = {},
            episodePlayerState = EpisodePlayerState().copy(
                errorMessage = "this is error message"
            )
        )
    }
}
