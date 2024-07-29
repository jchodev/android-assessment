package com.jerry.assessment.compose.podcastdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.R
import com.jerry.assessment.compose.common.CoilLoadingCompose
import com.jerry.assessment.compose.common.shimmerEffect
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.ext.toDateString
import com.jerry.assessment.ext.toDuration
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.EpisodePlayerState

@Composable
fun EpisodeListItemView(
    podcast: Podcast,
    episode: Episode,
    onClick: () -> Unit = {},
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
        ,
        leadingContent = {
            SubcomposeAsyncImage(
                model = podcast.imageUrl,
                modifier = Modifier
                    .width(56.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.small),
                contentDescription = podcast.title,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .width(56.dp)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.small)
                            .shimmerEffect()
                    )
                },
            )
        },
        headlineContent = {
            Column {
                Text(
                    text = episode.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.release_on, episode.published.toDateString()),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

                Text(
                    text = stringResource(id = R.string.duration, episode.duration.toDuration()),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        },
        trailingContent = {
            IconButton(onClick = onClick ) {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@DevicePreviews
@Composable
private fun EpisodeListItemViewPreview(){
    AssessmentprojectTheme {
        EpisodeListItemView(
            episode = Episode(
                title = "this is episode title",
                description = "this is episode description"
            ),
            podcast = Podcast(
                title = "this is podcast title",
                description = "this is podcast description"
            )
        )
    }
}
