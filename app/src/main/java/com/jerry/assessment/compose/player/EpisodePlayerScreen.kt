package com.jerry.assessment.compose.player

import android.annotation.SuppressLint
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.R
import com.jerry.assessment.compose.common.AssessmentTopBar
import com.jerry.assessment.compose.common.CoilLoadingCompose
import com.jerry.assessment.compose.common.EpisodeLinerProgressBar
import com.jerry.assessment.compose.common.ErrorDialog
import com.jerry.assessment.compose.common.PlaybackStatusButton
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.contants.TEST_TAG_TITLE
import com.jerry.assessment.data.Episode
import com.jerry.assessment.ext.toDuration
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState
import com.jerry.assessment.viewmodels.EpisodePlayerViewModel


@Composable
fun EpisodePlayerScreen(
    episode: Episode,
    viewModel: EpisodePlayerViewModel,
    onBackClick: () -> Unit,
    onPlayVideoClick: (Episode) -> Unit,
) {
    val state = viewModel.episodePlayerState.collectAsStateWithLifecycle().value

    val selectedEpisode = viewModel.selectedEpisode.collectAsStateWithLifecycle().value

    LaunchedEffect(episode) {
        //setSelectedEpisode is allow click by Episode Player Bar for display episode content on UI
        viewModel.setSelectedEpisode(episode)
    }

    EpisodePlayerScreen(
        selectedEpisode = selectedEpisode ?: episode,
        episodePlayerState = state,
        onBackClick = onBackClick,
        onPlayPause = {
            viewModel.playPause(episode)
        },
        onPlayVideoClick = onPlayVideoClick,
        onErrorDismissRequest = {
            viewModel.clearError()
        }
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodePlayerScreen(
    selectedEpisode: Episode,
    episodePlayerState: EpisodePlayerState,
    onBackClick: () -> Unit = {},
    onPlayPause: () -> Unit = {},
    onPlayVideoClick: (Episode) -> Unit = {},
    onErrorDismissRequest: () -> Unit = {},
) {
    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            AssessmentTopBar(
                modifier = Modifier
                    .shadow(elevation = 4.dp),
                title = selectedEpisode.title,
                titleCompose = {
                    Text(
                        //androidx.compose.foundation:foundation
                        modifier = Modifier.testTag(TEST_TAG_TITLE).basicMarquee(),
                        text = selectedEpisode.title,
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                showBack = true,
                onBackClick = onBackClick
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            EpisodePayerScreenContent(
                displayEpisode = selectedEpisode,
                episodePlayerState = episodePlayerState,
                onPlayPause = onPlayPause,
                onPlayVideoClick = onPlayVideoClick
            )

            episodePlayerState.errorMessage?.let {
                ErrorDialog(
                    text = it,
                    onRetryRequest = onPlayPause,
                    onDismissRequest = onErrorDismissRequest,
                )
            }
        }
    }
}

@Composable
private fun EpisodePayerScreenContent(
    displayEpisode: Episode,
    episodePlayerState: EpisodePlayerState,
    onPlayPause: () -> Unit = {},
    onPlayVideoClick: (Episode) -> Unit = {},
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            //image
            SubcomposeAsyncImage(
                model = displayEpisode.podcast.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = displayEpisode.title,
                contentScale = ContentScale.Crop,
                loading = {
                    CoilLoadingCompose()
                },
            )
        }
        item {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = displayEpisode.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = displayEpisode.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.height(8.dp))
            }
        }

        item {
            if (episodePlayerState.episode?.id == displayEpisode.id){
                EpisodeLinerProgressBar(
                    currentPosition = episodePlayerState.currentPosition,
                    duration = episodePlayerState.duration,
                    displayDuration = displayEpisode.duration.toDuration(),
                )
            }
            else {
                if (!displayEpisode.mimeType.startsWith("video/")) {
                    EpisodeLinerProgressBar(
                        currentPosition = 0,
                        duration = episodePlayerState.duration,
                        displayDuration = displayEpisode.duration.toDuration(),
                    )
                }
            }
        }

        //play or pause icon
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (displayEpisode.mimeType.startsWith("video/")){
                    Button(onClick = { onPlayVideoClick.invoke(
                        displayEpisode
                    ) }) {
                        Text(stringResource(id = R.string.play_video))
                    }
                } else {
                    if (episodePlayerState.episode?.id == displayEpisode.id) {
                        PlaybackStatusButton(
                            onClick = {
                                onPlayPause.invoke()
                            },
                            playbackState = episodePlayerState.playbackState,
                            isPlaying = episodePlayerState.isPlaying,
                            episodeTitle = episodePlayerState.episode.title
                        )
                    } else {
                        PlaybackStatusButton(
                            onClick = {
                                onPlayPause.invoke()
                            },
                            episodeTitle = displayEpisode.title
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}


@DevicePreviews
@Composable
private fun EpisodePlayerScreenPreview(){
    AssessmentprojectTheme {
        EpisodePlayerScreen(
            selectedEpisode = Episode(
                title = "this is title",
                description = "this is description"
            ),
            episodePlayerState = EpisodePlayerState()
        )
    }
}


@DevicePreviews
@Composable
private fun EpisodePlayerScreenWithPlayVideoButtonPreview(){
    AssessmentprojectTheme {
        EpisodePlayerScreen(
            selectedEpisode = Episode(
                title = "this is title",
                description = "this is description",
                mimeType = "video/mp4"
            ),
            episodePlayerState = EpisodePlayerState()
        )
    }
}


@DevicePreviews
@Composable
private fun EpisodePlayerScreenErrorMessagePreview(){
    AssessmentprojectTheme {
        EpisodePlayerScreen(
            selectedEpisode = Episode(
                title = "this is title",
                description = "this is description"
            ),
            episodePlayerState = EpisodePlayerState().copy(
                errorMessage = "this is error message"
            )
        )
    }
}

