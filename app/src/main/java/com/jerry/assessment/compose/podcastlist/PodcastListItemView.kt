package com.jerry.assessment.compose.podcastlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.compose.common.CoilLoadingCompose
import com.jerry.assessment.compose.common.shimmerEffect
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

@Composable
fun PodcastListItemView(
    podcast: Podcast,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
        ,
    ) {
        SubcomposeAsyncImage(
            model = podcast.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.small)
            ,
            contentDescription = podcast.title,
            contentScale = ContentScale.Crop,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.small)
                        .shimmerEffect()
                )
            },
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = podcast.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = podcast.author,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}


@DevicePreviews
@Composable
private fun PodcastListItemViewPreview() {
    AssessmentprojectTheme {
        PodcastListItemView (
            podcast = Podcast(
                id = 0,
                author = "auth 1",
                description = "description 1",
                imageUrl = "image 1",
                title = "title 1",
                link = "link 1",
            ),
            onClick = {}
        )
    }
}
