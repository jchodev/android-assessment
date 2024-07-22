package com.jerry.assessment.compose.podcastdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.R
import com.jerry.assessment.compose.common.AssessmentTopBar
import com.jerry.assessment.compose.common.CoilLoadingCompose
import com.jerry.assessment.compose.common.ErrorDialog
import com.jerry.assessment.compose.common.LoadingCompose
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.data.Episode
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.ui.theme.AssessmentprojectTheme
import com.jerry.assessment.viewmodels.PodcastDetailState
import com.jerry.assessment.viewmodels.PodcastDetailViewModel


@Composable
fun PodcastDetailScreen(
    selectedPodcast: Podcast,
    viewModel: PodcastDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onEpisodeClick: (Episode) -> Unit,
){
    val state = viewModel.podcastDetailState.collectAsStateWithLifecycle().value

    LaunchedEffect(selectedPodcast) {
        viewModel.getPodcastsByPodcast(selectedPodcast)
    }

    PodcastDetailScreen(
        podcastDetailState = state,
        selectedPodcast = selectedPodcast,
        onBackClick = onBackClick,
        onRefresh = {
            viewModel.getPodcastsByPodcast(selectedPodcast)
        },
        onEpisodeClick = onEpisodeClick,
        onErrorDismissRequest = {
            viewModel.clearError()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodcastDetailScreen(
    podcastDetailState: PodcastDetailState,
    selectedPodcast: Podcast,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit,
    onEpisodeClick: (Episode) -> Unit,
    onErrorDismissRequest: () -> Unit = {},
){

    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            AssessmentTopBar(
                modifier = Modifier
                    .shadow(elevation = 4.dp),
                //title = stringResource(id = R.string.podcast_list_title),
                title = selectedPodcast.title,
                showBack = true,
                onBackClick = onBackClick,
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh, contentDescription = stringResource(id = R.string.refresh))
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){
            PodcastDetailScreenContent(
                selectedPodcast = selectedPodcast,
                episodes = podcastDetailState.data,
                onEpisodeClick = onEpisodeClick,
            )

            if (podcastDetailState.isLoading) {
                LoadingCompose()
            }
            podcastDetailState.errorMessage?.let {
                ErrorDialog(
                    text = it,
                    onRetryRequest = onRefresh,
                    onDismissRequest = onErrorDismissRequest,
                )
            }
        }
    }
}

@Composable
private fun PodcastDetailScreenContent(
    selectedPodcast: Podcast,
    episodes: List<Episode> = emptyList(),
    onEpisodeClick: (Episode) -> Unit = {},
) {
    LazyColumn {
        item {
            //image
            SubcomposeAsyncImage(
                model = selectedPodcast.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = selectedPodcast.title,
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
                    text = selectedPodcast.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = selectedPodcast.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.episodes),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
        item {
            HorizontalDivider()
        }
        episodes.forEachIndexed { index, episode ->
            item {
                EpisodeListItemView(
                    podcast = selectedPodcast,
                    episode = episode,
                    onClick = {
                        onEpisodeClick.invoke(
                            episode.copy(
                                podcast = selectedPodcast
                            )
                        )
                    }
                )
                if (index < episodes.size - 1) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@DevicePreviews
@Composable
private fun PodcastDetailScreenLoadingPreview(){
    AssessmentprojectTheme {
        PodcastDetailScreen(
            podcastDetailState = PodcastDetailState().copy(
                isLoading = true
            ),
            selectedPodcast = Podcast(
                title = "this is podcast title",
                description = "this is podcast description",
            ),
            onBackClick = {},
            onRefresh = {},
            onEpisodeClick = {},
            onErrorDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PodcastDetailScreenErrorPreview(){
    AssessmentprojectTheme {
        PodcastDetailScreen(
            podcastDetailState = PodcastDetailState().copy(
                errorMessage = "this is error"
            ),
            selectedPodcast = Podcast(
                title = "this is podcast title",
                description = "this is podcast description"
            ),
            onBackClick = {},
            onRefresh = {},
            onEpisodeClick = {},
            onErrorDismissRequest = {},
        )
    }
}

@DevicePreviews
@Composable
private fun PodcastDetailScreenSuccessPreview(){
    AssessmentprojectTheme {
        PodcastDetailScreen(
            podcastDetailState = PodcastDetailState(
                data = listOf(
                    Episode(
                        title = "this is Episode title 1",
                        description = "this is Episode description 1"
                    ),
                    Episode(
                        title = "this is Episode title 2",
                        description = "this is Episode description 2"
                    )
                )
            ),
            selectedPodcast = Podcast(
                title = "this is podcast title",
                description = "this is podcast description"
            ),
            onBackClick = {},
            onRefresh = {},
            onEpisodeClick = {},
            onErrorDismissRequest = {},
        )
    }
}
