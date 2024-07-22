package com.jerry.assessment.compose.player


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player

import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.R

import com.jerry.assessment.compose.common.CoilLoadingCompose
import com.jerry.assessment.compose.common.PlaybackStatusButton
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.contants.TEST_TAG_PLAYBACK_BAR_ERROR
import com.jerry.assessment.data.Episode
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState

@Composable
fun EpisodePlayerBar(
    episodePlayerState: EpisodePlayerState,
    onPlayPauseClick: () -> Unit = {},
    onBarClick:() -> Unit = {},
) {
    Card(
        modifier = Modifier
            .clickable {
                onBarClick.invoke()
            }
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.small
    ) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth(),
            leadingContent = {
                SubcomposeAsyncImage(
                    model = episodePlayerState.episode?.podcast?.imageUrl,
                    modifier = Modifier
                        .width(48.dp)
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.small),
                    contentDescription = episodePlayerState.episode?.title,
                    contentScale = ContentScale.Crop,
                    loading = {
                        CoilLoadingCompose()
                    },
                )
            },
            headlineContent = {
                Column {
                    Text(
                        text =  episodePlayerState.episode?.title ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = episodePlayerState.episode?.description ?: "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                    )

                }
            },
            trailingContent = {
                PlaybackStatusButton(
                    onClick = onPlayPauseClick,
                    playbackState = episodePlayerState.playbackState,
                    isPlaying = episodePlayerState.isPlaying,
                    episodeTitle = episodePlayerState.episode?.title ?: "",
                )
            }
        )

        episodePlayerState.errorMessage?.let { errorMessage ->
            Box(
                modifier = Modifier
                    .testTag(TEST_TAG_PLAYBACK_BAR_ERROR)
                    .background(color = MaterialTheme.colorScheme.errorContainer)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.error_with, errorMessage),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }
}

@DevicePreviews
@Composable
private fun EpisodePlayerBarLoadingPreview(){
    AssessmentprojectTheme {
        EpisodePlayerBar(
            episodePlayerState = EpisodePlayerState().copy(
                playbackState = Player.STATE_BUFFERING,
                episode = Episode(
                    title = "Episode title test",
                    description = "Episode description test",
                )
            )
        )
    }
}

@DevicePreviews
@Composable
private fun EpisodePlayerBarErrorPreview(){
    AssessmentprojectTheme {
        EpisodePlayerBar(
            episodePlayerState = EpisodePlayerState().copy(
                playbackState = Player.STATE_READY,
                episode = Episode(
                    title = "Episode title test",
                    description = "Episode description test",
                ),
                errorMessage = "this is error message"
            )
        )
    }
}


@DevicePreviews
@Composable
private fun EpisodePlayerBarPlayingStatePreview(){
    AssessmentprojectTheme {
        EpisodePlayerBar(
            episodePlayerState = EpisodePlayerState().copy(
                playbackState = Player.STATE_READY,
                isPlaying = true,
                episode = Episode(
                    title = "Episode title test",
                    description = "Episode description test",
                )
            )
        )
    }
}

@DevicePreviews
@Composable
private fun EpisodePlayerBarPauseStatePreview(){
    AssessmentprojectTheme {
        EpisodePlayerBar(
            episodePlayerState = EpisodePlayerState().copy(
                playbackState = Player.STATE_READY,
                isPlaying = false,
                episode = Episode(
                    title = "Episode title test",
                    description = "Episode description test",
                )
            )
        )
    }
}