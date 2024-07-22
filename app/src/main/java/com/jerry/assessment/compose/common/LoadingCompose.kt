package com.jerry.assessment.compose.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.jerry.assessment.compose.preview.DevicePreviews
import com.jerry.assessment.contants.TEST_TAG_LOADING
import com.jerry.assessment.ui.theme.AssessmentprojectTheme

@Composable
fun LoadingCompose(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .testTag(TEST_TAG_LOADING)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@DevicePreviews
@Composable
private fun ErrorDialogPreview(){
    AssessmentprojectTheme {
        LoadingCompose()
    }
}