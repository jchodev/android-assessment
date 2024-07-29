package com.jerry.assessment.compose.podcastlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.contants.TEST_TAG_LOADING
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

@Composable
fun PodcastListLoadingScreen() {

    LazyVerticalGrid(
        modifier = Modifier.testTag(TEST_TAG_LOADING),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 24.dp
        )
    ) {
        for (i in 1..6) {
            item {
                PodcastListItemLoadingView()
            }
        }

    }
}

@DevicePreviews
@Composable
private fun PodcastListItemViewPreview() {
    AssessmentprojectTheme {
        PodcastListLoadingScreen ()
    }
}
