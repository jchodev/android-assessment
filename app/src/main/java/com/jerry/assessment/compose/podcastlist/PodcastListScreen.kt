package com.jerry.assessment.compose.podcastlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jerry.assessment.viewmodels.PodcastListViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jerry.assessment.R
import com.jerry.assessment.compose.common.AssessmentTopBar
import com.jerry.assessment.compose.common.LoadingCompose
import com.jerry.assessment.data.Podcast
import com.jerry.assessment.viewmodels.PodcastListState
//import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import com.jerry.assessment.compose.common.ErrorDialog
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.ui.theme.AssessmentprojectTheme


@Composable
fun PodcastListScreen(
    viewModel: PodcastListViewModel = hiltViewModel(),
    onPodcastClick: (Podcast) -> Unit = {},
) {
    val state = viewModel.podcastListState.collectAsStateWithLifecycle().value
    PodcastListScreen(
        podcastListState = state,
        onPodcastClick = {
            onPodcastClick.invoke(it)
        },
        onRefresh = {
            viewModel.getData()
        },
        onErrorDismissRequest = {
            viewModel.clearError()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PodcastListScreen(
    podcastListState: PodcastListState,
    onPodcastClick: (Podcast) -> Unit,
    onRefresh: () -> Unit,
    onErrorDismissRequest: () -> Unit,
) {
    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            AssessmentTopBar(
                modifier = Modifier
                    .shadow(elevation = 4.dp),
                title = stringResource(id = R.string.podcast_list_title),
                showBack = false,
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Filled.Refresh, contentDescription = stringResource(id = R.string.refresh))
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){

            PodcastListScreenContent(
                podcasts = podcastListState.data,
                onPodcastClick = onPodcastClick
            )

            if (podcastListState.isLoading) {
                PodcastListLoadingScreen()
            }


            podcastListState.errorMessage?.let {
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
private fun PodcastListScreenContent(
    podcasts: List<Podcast> = emptyList(),
    onPodcastClick: (Podcast) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 24.dp
        )
    ) {
        items(
            items = podcasts,
            key = { it.id }
        ) { podcast ->
            PodcastListItemView(
                podcast = podcast,
                onClick = {
                    onPodcastClick.invoke(podcast)
                }
            )
        }
    }
}

@DevicePreviews
@Composable
private fun PodcastListScreenPreview() {
    AssessmentprojectTheme {
        PodcastListScreen (
            onPodcastClick = {},
            onRefresh = {},
            onErrorDismissRequest = {},
            podcastListState = PodcastListState().copy(
                data = listOf(
                    Podcast(
                        id = 0,
                        author = "auth 1",
                        description = "description 1",
                        imageUrl = "image 1",
                        title = "title 1",
                        link = "link 1",
                    ),
                    Podcast(
                        id = 1,
                        author = "auth 2",
                        description = "description 2",
                        imageUrl = "image 2",
                        title = "title 2",
                        link = "link 2",
                    ),
                )
            )
        )
    }
}

@DevicePreviews
@Composable
private fun PodcastListScreenLoadingPreview() {
    AssessmentprojectTheme {
        PodcastListScreen (
            onPodcastClick = {},
            onRefresh = {},
            onErrorDismissRequest = {},
            podcastListState = PodcastListState().copy(
                isLoading = true
            )
        )
    }
}

@DevicePreviews
@Composable
private fun PodcastListScreenErrorPreview() {
    AssessmentprojectTheme {
        PodcastListScreen (
            onPodcastClick = {},
            onRefresh = {},
            onErrorDismissRequest = {},
            podcastListState = PodcastListState().copy(
                errorMessage = "this is error mesage"
            )
        )
    }
}